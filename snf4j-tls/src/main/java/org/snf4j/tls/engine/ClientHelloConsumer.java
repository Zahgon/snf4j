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

import static org.snf4j.tls.IntConstant.findMatch;
import static org.snf4j.tls.IntConstant.find;
import static org.snf4j.tls.extension.ExtensionsUtil.find;
import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.snf4j.core.session.ssl.ClientAuth;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.DecryptErrorAlert;
import org.snf4j.tls.alert.HandshakeFailureAlert;
import org.snf4j.tls.alert.IllegalParameterAlert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.alert.MissingExtensionAlert;
import org.snf4j.tls.alert.ProtocolVersionAlert;
import org.snf4j.tls.alert.UnexpectedMessageAlert;
import org.snf4j.tls.alert.UnrecognizedNameAlert;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.crypto.Hkdf;
import org.snf4j.tls.crypto.IHash;
import org.snf4j.tls.crypto.IHkdf;
import org.snf4j.tls.crypto.IKeyExchange;
import org.snf4j.tls.crypto.ITranscriptHash;
import org.snf4j.tls.crypto.KeySchedule;
import org.snf4j.tls.crypto.TranscriptHash;
import org.snf4j.tls.extension.ALPNExtension;
import org.snf4j.tls.extension.EarlyDataExtension;
import org.snf4j.tls.extension.ExtensionType;
import org.snf4j.tls.extension.IExtension;
import org.snf4j.tls.extension.IKeyShareExtension;
import org.snf4j.tls.extension.IPreSharedKeyExtension;
import org.snf4j.tls.extension.IPskKeyExchangeModesExtension;
import org.snf4j.tls.extension.IServerNameExtension;
import org.snf4j.tls.extension.ISignatureAlgorithmsExtension;
import org.snf4j.tls.extension.ISupportedGroupsExtension;
import org.snf4j.tls.extension.ISupportedVersionsExtension;
import org.snf4j.tls.extension.KeyShareEntry;
import org.snf4j.tls.extension.KeyShareExtension;
import org.snf4j.tls.extension.NamedGroup;
import org.snf4j.tls.extension.ParsedKey;
import org.snf4j.tls.extension.PreSharedKeyExtension;
import org.snf4j.tls.extension.PskKeyExchangeMode;
import org.snf4j.tls.extension.ServerNameExtension;
import org.snf4j.tls.extension.SignatureAlgorithmsCertExtension;
import org.snf4j.tls.extension.SignatureAlgorithmsExtension;
import org.snf4j.tls.extension.SignatureScheme;
import org.snf4j.tls.extension.SupportedGroupsExtension;
import org.snf4j.tls.extension.SupportedVersionsExtension;
import org.snf4j.tls.handshake.Certificate;
import org.snf4j.tls.handshake.CertificateRequest;
import org.snf4j.tls.handshake.CertificateType;
import org.snf4j.tls.handshake.CertificateVerify;
import org.snf4j.tls.handshake.EncryptedExtensions;
import org.snf4j.tls.handshake.Finished;
import org.snf4j.tls.handshake.HandshakeType;
import org.snf4j.tls.handshake.IClientHello;
import org.snf4j.tls.handshake.IHandshake;
import org.snf4j.tls.handshake.ServerHello;
import org.snf4j.tls.handshake.ServerHelloRandom;
import org.snf4j.tls.record.RecordType;
import org.snf4j.tls.session.SessionTicket;
import org.snf4j.tls.session.UsedSession;

public class ClientHelloConsumer implements IHandshakeConsumer {

    @Override
    public HandshakeType getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void consume(EngineState state, IHandshake handshake, ByteBuffer[] data, boolean isHRR) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class KeyExchangeTask extends AbstractEngineTask {

        private final NamedGroup namedGroup;

        private final ParsedKey parsedKey;

        private final byte[] legacySessionId;

        private final int selectedIdentity;

        private final SecureRandom secureRandom;

        private volatile byte[] secret;

        private volatile byte[] random;

        private volatile PublicKey publicKey;

        KeyExchangeTask(NamedGroup namedGroup, ParsedKey parsedKey, byte[] legacySessionId, int selectedIdentity, SecureRandom secureRandom) {
            this.namedGroup = namedGroup;
            this.parsedKey = parsedKey;
            this.legacySessionId = legacySessionId;
            this.selectedIdentity = selectedIdentity;
            this.secureRandom = secureRandom;
        }

        @Override
        public boolean isProducing() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String name() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        void execute() throws Exception {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void finish(EngineState state) throws Alert {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class CertificateTask extends AbstractCertificateTask {

        CertificateTask(ICertificateSelector selector, CertificateCriteria criteria) {
            super(selector, criteria);
        }

        CertificateTask() {
        }

        @Override
        public void finish(EngineState state) throws Alert {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
