/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2023-2024 SNF4J contributors
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

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import org.snf4j.tls.IntConstant;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.DecryptErrorAlert;
import org.snf4j.tls.alert.IllegalParameterAlert;
import org.snf4j.tls.alert.UnexpectedMessageAlert;
import org.snf4j.tls.extension.SignatureScheme;
import org.snf4j.tls.handshake.HandshakeType;
import org.snf4j.tls.handshake.ICertificate;
import org.snf4j.tls.handshake.ICertificateEntry;
import org.snf4j.tls.handshake.ICertificateVerify;
import org.snf4j.tls.handshake.IHandshake;

public class CertificateVerifyConsumer implements IHandshakeConsumer {

    @Override
    public HandshakeType getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static SignatureScheme[] cloneCertSchemes(IEngineParameters params) {
        SignatureScheme[] certSchemes = params.getCertSignatureSchemes();
        if (certSchemes == null) {
            certSchemes = params.getSignatureSchemes();
        }
        return certSchemes.clone();
    }

    private void consumeClient(EngineState state, ICertificateVerify certificateVerify, ByteBuffer[] data) throws Alert {
        IEngineParameters params = state.getParameters();
        SignatureScheme algorithm = IntConstant.find(params.getSignatureSchemes(), certificateVerify.getAlgorithm());
        if (algorithm == null) {
            throw new IllegalParameterAlert("Unexpected signature algorithm");
        }
        ICertificate certificate = state.getRetainedHandshake();
        AbstractEngineTask task = new CertificateTask(state.getHandler().getCertificateValidator(), new CertificateValidateCriteria(false, params.getPeerHost(), cloneCertSchemes(params)), certificate.getEntries(), algorithm, certificateVerify.getSignature(), state.getTranscriptHash().getHash(HandshakeType.CERTIFICATE, false), false);
        state.getTranscriptHash().update(certificateVerify.getType(), data);
        state.retainHandshake(null);
        if (params.getDelegatedTaskMode().certificates()) {
            state.changeState(MachineState.CLI_WAIT_TASK);
            state.addTask(task);
        } else {
            task.run(state);
        }
    }

    private void consumeServer(EngineState state, ICertificateVerify certificateVerify, ByteBuffer[] data) throws Alert {
        IEngineParameters params = state.getParameters();
        SignatureScheme algorithm = IntConstant.find(params.getSignatureSchemes(), certificateVerify.getAlgorithm());
        if (algorithm == null) {
            throw new IllegalParameterAlert("Unexpected signature algorithm");
        }
        ICertificate certificate = state.getRetainedHandshake();
        AbstractEngineTask task = new CertificateTask(state.getHandler().getCertificateValidator(), new CertificateValidateCriteria(true, state.getHostName(), cloneCertSchemes(params)), certificate.getEntries(), algorithm, certificateVerify.getSignature(), state.getTranscriptHash().getHash(HandshakeType.CERTIFICATE, true), true);
        state.getTranscriptHash().update(certificateVerify.getType(), data);
        state.retainHandshake(null);
        if (params.getDelegatedTaskMode().certificates()) {
            state.changeState(MachineState.SRV_WAIT_TASK);
            state.addTask(task);
        } else {
            task.run(state);
        }
    }

    @Override
    public void consume(EngineState state, IHandshake handshake, ByteBuffer[] data, boolean isHRR) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class CertificateTask extends AbstractEngineTask {

        private final ICertificateValidator validator;

        private final ICertificateEntry[] entries;

        private final CertificateValidateCriteria criteria;

        private final SignatureScheme algorithm;

        private final byte[] signature;

        private final byte[] content;

        private final boolean client;

        private volatile X509Certificate[] certs;

        private volatile Alert alert;

        CertificateTask(ICertificateValidator validator, CertificateValidateCriteria criteria, ICertificateEntry[] entries, SignatureScheme algorithm, byte[] signature, byte[] content, boolean client) {
            this.validator = validator;
            this.criteria = criteria;
            this.entries = entries;
            this.algorithm = algorithm;
            this.signature = signature;
            this.content = content;
            this.client = client;
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
