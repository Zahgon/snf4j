/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022-2024 SNF4J contributors
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * -----------------------------------------------------------------------------
 */
package org.snf4j.tls.engine;

import static org.snf4j.tls.extension.ExtensionsUtil.find;
import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.snf4j.tls.IntConstant;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.IllegalParameterAlert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.alert.MissingExtensionAlert;
import org.snf4j.tls.alert.ProtocolVersionAlert;
import org.snf4j.tls.alert.UnexpectedMessageAlert;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.cipher.IHashSpec;
import org.snf4j.tls.crypto.Hkdf;
import org.snf4j.tls.crypto.IHash;
import org.snf4j.tls.crypto.IHkdf;
import org.snf4j.tls.crypto.IKeyExchange;
import org.snf4j.tls.crypto.ITranscriptHash;
import org.snf4j.tls.crypto.KeySchedule;
import org.snf4j.tls.crypto.TranscriptHash;
import org.snf4j.tls.extension.ExtensionType;
import org.snf4j.tls.extension.ExtensionsUtil;
import org.snf4j.tls.extension.ICookieExtension;
import org.snf4j.tls.extension.IExtension;
import org.snf4j.tls.extension.IKeyShareExtension;
import org.snf4j.tls.extension.IPreSharedKeyExtension;
import org.snf4j.tls.extension.ISupportedGroupsExtension;
import org.snf4j.tls.extension.ISupportedVersionsExtension;
import org.snf4j.tls.extension.KeyShareEntry;
import org.snf4j.tls.extension.KeyShareExtension;
import org.snf4j.tls.extension.NamedGroup;
import org.snf4j.tls.extension.OfferedPsk;
import org.snf4j.tls.extension.PreSharedKeyExtension;
import org.snf4j.tls.extension.PskIdentity;
import org.snf4j.tls.handshake.HandshakeType;
import org.snf4j.tls.handshake.IClientHello;
import org.snf4j.tls.handshake.IHandshake;
import org.snf4j.tls.handshake.IServerHello;
import org.snf4j.tls.record.RecordType;
import org.snf4j.tls.session.SessionTicket;

public class ServerHelloConsumer implements IHandshakeConsumer {

    @Override
    public HandshakeType getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void consumeHRR(EngineState state, IServerHello serverHello, ByteBuffer[] data, IKeyShareExtension keyShare, int noPskCount) throws Alert {
        IClientHello clientHello = state.getRetainedHandshake();
        List<IExtension> extensions = clientHello.getExtensions();
        List<PskContext> pskCtxs = state.getPskContexts();
        boolean psk = false;
        boolean changed = false;
        if (pskCtxs != null) {
            long time = System.currentTimeMillis();
            int size = pskCtxs.size();
            OfferedPsk[] psks = new OfferedPsk[size - noPskCount];
            for (int i = size - 1; i >= 0; i--) {
                PskContext pskCtx = pskCtxs.get(i);
                SessionTicket ticket = pskCtx.getTicket();
                if (ticket != null) {
                    long obfuscatedAge = (time - ticket.getCreationTime() + ticket.getAgeAdd()) % 0x100000000L;
                    psks[i] = new OfferedPsk(new PskIdentity(ticket.getTicket(), obfuscatedAge), new byte[ticket.getCipherSuite().spec().getHashSpec().getHashLength()]);
                }
                if (i == 0) {
                    pskCtx.getKeySchedule().getTranscriptHash().updateHelloRetryRequest(data);
                } else {
                    int len = data.length;
                    ByteBuffer[] dupData = new ByteBuffer[len];
                    for (int j = 0; j < len; ++j) {
                        dupData[j] = data[j].duplicate();
                    }
                    pskCtx.getKeySchedule().getTranscriptHash().updateHelloRetryRequest(dupData);
                }
            }
            psk = true;
            extensions.set(extensions.size() - 1, new PreSharedKeyExtension(psks));
            changed = true;
        } else {
            state.getTranscriptHash().updateHelloRetryRequest(data);
        }
        NamedGroup namedGroup = null;
        if (keyShare != null) {
            int size = extensions.size();
            NamedGroup group = keyShare.getNamedGroup();
            boolean keyShareFound = false;
            for (int i = 0; i < size; ++i) {
                IExtension extension = extensions.get(i);
                if (extension.getType().equals(ExtensionType.KEY_SHARE)) {
                    PrivateKey key = state.getPrivateKey(group, true);
                    keyShareFound = true;
                    if (key == null) {
                        ISupportedGroupsExtension suppGroups = find(clientHello, ExtensionType.SUPPORTED_GROUPS);
                        if (IntConstant.find(suppGroups.getGroups(), group) == null) {
                            throw new IllegalParameterAlert("Unexpected supported group in HelloRetryRequest");
                        }
                        namedGroup = group;
                    } else {
                        KeyShareEntry[] entries = ((IKeyShareExtension) extension).getEntries();
                        if (entries.length > 1) {
                            int j = 0;
                            for (; ; ) {
                                KeyShareEntry entry = entries[j++];
                                if (entry.getNamedGroup().equals(group)) {
                                    extensions.set(i, new KeyShareExtension(IKeyShareExtension.Mode.CLIENT_HELLO, entry));
                                    break;
                                }
                            }
                            state.addPrivateKey(group, key);
                            changed = true;
                        }
                    }
                    break;
                }
            }
            if (!keyShareFound) {
                throw new InternalErrorAlert("No key share extension in stored ClientHello");
            }
        }
        ICookieExtension cookie = find(serverHello, ExtensionType.COOKIE);
        if (cookie != null) {
            extensions.add(0, cookie);
            changed = true;
        }
        IEarlyDataContext ctx = state.getEarlyDataContext();
        if (ctx.getState() == EarlyDataState.PROCESSING) {
            int typeValue = ExtensionType.EARLY_DATA.value();
            for (Iterator<IExtension> i = extensions.iterator(); i.hasNext(); ) {
                if (i.next().getType().value() == typeValue) {
                    i.remove();
                    break;
                }
            }
            ctx.rejecting();
            state.getHandler().getEarlyDataHandler().rejectedEarlyData();
        }
        if (namedGroup == null) {
            if (!changed) {
                throw new IllegalParameterAlert("No change after HelloRetryRequest");
            }
            //CCS before second ClientHello (early data not offered)
            if (state.getParameters().isCompatibilityMode() && state.getEarlyDataContext().getState() == EarlyDataState.NONE) {
                state.getListener().produceChangeCipherSpec(state);
            }
            if (psk) {
                produceWithBinders(state, RecordType.INITIAL);
            } else {
                ConsumerUtil.produce(state, clientHello, RecordType.INITIAL);
            }
        } else {
            KeyExchangeTask task = new KeyExchangeTask(namedGroup, psk, state.getHandler().getSecureRandom());
            if (state.getParameters().getDelegatedTaskMode().all()) {
                state.changeState(MachineState.CLI_WAIT_TASK);
                state.addTask(task);
            } else {
                task.run(state);
            }
        }
    }

    private static void produceWithBinders(EngineState state, RecordType recordType) throws Alert {
        IClientHello clientHello = state.getRetainedHandshake();
        IPreSharedKeyExtension preSharedKey = ExtensionsUtil.findLast(clientHello);
        byte[] truncated = clientHello.prepare();
        OfferedPsk[] psks = preSharedKey.getOfferedPsks();
        int truncatedLength = truncated.length - PreSharedKeyExtension.bindersLength(psks);
        int i = 0;
        try {
            for (PskContext pskCtx : state.getPskContexts()) {
                if (pskCtx.getTicket() != null) {
                    byte[] binder = pskCtx.getKeySchedule().computePskBinder(truncated, truncatedLength);
                    System.arraycopy(binder, 0, psks[i++].getBinder(), 0, binder.length);
                }
            }
            PreSharedKeyExtension.updateBinders(truncated, truncatedLength, psks);
        } catch (Exception e) {
            throw new InternalErrorAlert("Failed to bind PSK", e);
        }
        state.produce(new ProducedHandshake(clientHello, recordType));
    }

    static KeySchedule removeNoPskKeySchedule(EngineState state) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void consume(EngineState state, IHandshake handshake, ByteBuffer[] data, boolean isHRR) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class KeyExchangeTask extends AbstractEngineTask {

        private final NamedGroup namedGroup;

        private final boolean psk;

        private final SecureRandom random;

        private volatile KeyPair pair;

        KeyExchangeTask(NamedGroup namedGroup, boolean psk, SecureRandom random) {
            this.namedGroup = namedGroup;
            this.psk = psk;
            this.random = random;
        }

        @Override
        public String name() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isProducing() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void finish(EngineState state) throws Alert {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        void execute() throws Exception {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
