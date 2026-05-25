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

import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.IllegalParameterAlert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.alert.UnexpectedMessageAlert;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.crypto.Hkdf;
import org.snf4j.tls.crypto.IHash;
import org.snf4j.tls.crypto.IHkdf;
import org.snf4j.tls.crypto.ITranscriptHash;
import org.snf4j.tls.crypto.KeySchedule;
import org.snf4j.tls.crypto.TranscriptHash;
import org.snf4j.tls.extension.ALPNExtension;
import org.snf4j.tls.extension.EarlyDataExtension;
import org.snf4j.tls.extension.ExtensionValidator;
import org.snf4j.tls.extension.ExtensionsUtil;
import org.snf4j.tls.extension.IExtension;
import org.snf4j.tls.extension.IExtensionValidator;
import org.snf4j.tls.extension.IKeyShareExtension;
import org.snf4j.tls.extension.ISupportedVersionsExtension;
import org.snf4j.tls.extension.KeyShareEntry;
import org.snf4j.tls.extension.KeyShareExtension;
import org.snf4j.tls.extension.NamedGroup;
import org.snf4j.tls.extension.OfferedPsk;
import org.snf4j.tls.extension.PreSharedKeyExtension;
import org.snf4j.tls.extension.PskIdentity;
import org.snf4j.tls.extension.PskKeyExchangeMode;
import org.snf4j.tls.extension.PskKeyExchangeModesExtension;
import org.snf4j.tls.extension.ServerNameExtension;
import org.snf4j.tls.extension.SignatureAlgorithmsCertExtension;
import org.snf4j.tls.extension.SignatureAlgorithmsExtension;
import org.snf4j.tls.extension.SignatureScheme;
import org.snf4j.tls.extension.SupportedGroupsExtension;
import org.snf4j.tls.extension.SupportedVersionsExtension;
import org.snf4j.tls.handshake.ClientHello;
import org.snf4j.tls.handshake.HandshakeDecoder;
import org.snf4j.tls.handshake.HandshakeType;
import org.snf4j.tls.handshake.IHandshake;
import org.snf4j.tls.handshake.IHandshakeDecoder;
import org.snf4j.tls.handshake.IServerHello;
import org.snf4j.tls.handshake.KeyUpdate;
import org.snf4j.tls.handshake.ServerHelloRandom;
import org.snf4j.tls.record.RecordType;
import org.snf4j.tls.session.ISession;
import org.snf4j.tls.session.ISessionManager;
import org.snf4j.tls.session.SessionTicket;

public class HandshakeEngine implements IHandshakeEngine {

    private final static Random RANDOM = new Random();

    private final static IHandshakeConsumer[] CONSUMERS;

    private final static SessionTicket[] EMPTY = new SessionTicket[0];

    private static void addConsumer(IHandshakeConsumer[] consumers, IHandshakeConsumer consumer) {
        CONSUMERS[consumer.getType().value()] = consumer;
    }

    static {
        CONSUMERS = new IHandshakeConsumer[25];
        addConsumer(CONSUMERS, new ClientHelloConsumer());
        addConsumer(CONSUMERS, new ServerHelloConsumer());
        addConsumer(CONSUMERS, new EncryptedExtensionsConsumer());
        addConsumer(CONSUMERS, new CertificateRequestConsumer());
        addConsumer(CONSUMERS, new CertificateConsumer());
        addConsumer(CONSUMERS, new CertificateVerifyConsumer());
        addConsumer(CONSUMERS, new FinishedConsumer());
        addConsumer(CONSUMERS, new NewSessionTicketConsumer());
        addConsumer(CONSUMERS, new KeyUpdateConsumer());
        addConsumer(CONSUMERS, new EndOfEarlyDataConsumer());
    }

    private final IHandshakeDecoder decoder;

    private final IExtensionValidator extensionValidator;

    private final EngineState state;

    public HandshakeEngine(boolean clientMode, IEngineParameters parameters, IEngineHandler handler, IEngineStateListener listener) {
        this(clientMode, parameters, handler, listener, HandshakeDecoder.DEFAULT);
    }

    public HandshakeEngine(ISession session, IEngineParameters parameters, IEngineHandler handler, IEngineStateListener listener) {
        this(session, parameters, handler, listener, HandshakeDecoder.DEFAULT);
    }

    public HandshakeEngine(boolean clientMode, IEngineParameters parameters, IEngineHandler handler, IEngineStateListener listener, IHandshakeDecoder decoder) {
        this(clientMode, parameters, handler, listener, null, decoder);
    }

    public HandshakeEngine(ISession session, IEngineParameters parameters, IEngineHandler handler, IEngineStateListener listener, IHandshakeDecoder decoder) {
        this(true, parameters, handler, listener, session, decoder);
    }

    HandshakeEngine(boolean clientMode, IEngineParameters parameters, IEngineHandler handler, IEngineStateListener listener, ISession session, IHandshakeDecoder decoder) {
        this(new EngineState(clientMode ? MachineState.CLI_INIT : MachineState.SRV_INIT, parameters, handler, listener), decoder);
        state.setSession(session);
    }

    HandshakeEngine(EngineState state, IHandshakeDecoder decoder) {
        this.decoder = decoder;
        this.state = state;
        extensionValidator = ExtensionValidator.DEFAULT;
    }

    @Override
    public IEngineState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IEngineHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void consume(ByteBuffer[] srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void consume(ByteBufferArray srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean needProduce() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ProducedHandshake[] produce() throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean updateTasks() throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasProducingTask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasRunningTask(boolean onlyUndone) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasTask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Runnable getTask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void start() throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void updateKeys() throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void cleanup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class KeyExchangeTask extends AbstractEngineTask {

        private final NamedGroup[] namedGroups;

        private final SecureRandom secureRandom;

        private volatile KeyPair[] pairs;

        private volatile byte[] random;

        KeyExchangeTask(NamedGroup[] namedGroups, SecureRandom secureRandom) {
            this.namedGroups = namedGroups;
            this.secureRandom = secureRandom;
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
