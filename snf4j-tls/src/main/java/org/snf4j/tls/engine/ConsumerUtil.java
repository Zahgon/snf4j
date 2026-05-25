/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2023 SNF4J contributors
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

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Arrays;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.extension.SignatureScheme;
import org.snf4j.tls.handshake.IHandshake;
import org.snf4j.tls.record.RecordType;

public class ConsumerUtil {

    private final static byte[] SIGNATURE_64_OCTETS = new byte[64];

    private final static byte[] SIGNATURE_SERVER_CONTEXT = "TLS 1.3, server CertificateVerify".getBytes(StandardCharsets.US_ASCII);

    private final static byte[] SIGNATURE_CLIENT_CONTEXT = "TLS 1.3, client CertificateVerify".getBytes(StandardCharsets.US_ASCII);

    static {
        Arrays.fill(SIGNATURE_64_OCTETS, (byte) 32);
    }

    private ConsumerUtil() {
    }

    static void produce(EngineState state, IHandshake handshake, RecordType recordType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void prepare(EngineState state, IHandshake handshake, RecordType recordType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void prepare(EngineState state, IHandshake handshake, RecordType recordType, RecordType nextRecordType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void produceHRR(EngineState state, IHandshake handshake, RecordType recordType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static byte[] sign(byte[] content, SignatureScheme scheme, PrivateKey privateKey, boolean client, SecureRandom random) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean verify(byte[] signature, byte[] content, SignatureScheme scheme, PublicKey publicKey, boolean client) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
