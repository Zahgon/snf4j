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
package org.snf4j.tls.crypto;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import org.snf4j.tls.Args;
import org.snf4j.tls.cipher.ICipherSuiteSpec;
import org.snf4j.tls.cipher.IHashSpec;
import org.snf4j.tls.handshake.HandshakeType;

public class KeySchedule extends AbstractKeySchedule {

    private static final byte[] DERIVED = label("derived");

    private static final byte[] RESUMPTION = label("resumption");

    private static final byte[] RES_BINDER = label("res binder");

    private static final byte[] RES_MASTER = label("res master");

    private static final byte[] EXT_BINDER = label("ext binder");

    private static final byte[] C_E_TRAFFIC = label("c e traffic");

    private static final byte[] FINISHED = label("finished");

    private static final byte[] C_HS_TRAFFIC = label("c hs traffic");

    private static final byte[] S_HS_TRAFFIC = label("s hs traffic");

    private static final byte[] C_AP_TRAFFIC = label("c ap traffic");

    private static final byte[] S_AP_TRAFFIC = label("s ap traffic");

    private static final byte[] TRAFFIC_UPD = label("traffic upd");

    private static final byte[] KEY = label("key");

    private static final byte[] IV = label("iv");

    private static final byte[] EMPTY = new byte[0];

    private final ITranscriptHash transcriptHash;

    private final int hashLength;

    private final byte[] emptyHash;

    private boolean usingPsk;

    private boolean externalPsk;

    private byte[] earlySecret;

    private byte[] earlyTrafficSecret;

    private byte[] binderKey;

    private byte[] handshakeSecret;

    private byte[] clientHandshakeTrafficSecret;

    private byte[] serverHandshakeTrafficSecret;

    private byte[] masterSecret;

    private byte[] clientApplicationTrafficSecret;

    private byte[] serverApplicationTrafficSecret;

    private byte[] resumptionMasterSecret;

    private static void checkDerived(Object o, String name) {
        if (o == null) {
            throw new IllegalStateException(name + " not derived");
        }
    }

    public KeySchedule(IHkdf hkdf, ITranscriptHash transcriptHash, ICipherSuiteSpec cipherSuiteSpec) {
        super(hkdf, cipherSuiteSpec);
        Args.checkNull(transcriptHash, "transcriptHash");
        this.transcriptHash = transcriptHash;
        emptyHash = hashSpec.getEmptyHash();
        hashLength = hashSpec.getHashLength();
    }

    public KeySchedule(IHkdf hkdf, ITranscriptHash transcriptHash, IHashSpec hashSpec) {
        super(hkdf, hashSpec);
        Args.checkNull(transcriptHash, "transcriptHash");
        this.transcriptHash = transcriptHash;
        emptyHash = hashSpec.getEmptyHash();
        hashLength = hashSpec.getHashLength();
    }

    public ITranscriptHash getTranscriptHash() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isUsingPsk() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveEarlySecret(byte[] psk, boolean externalPsk) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveEarlySecret() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseEarlySecret() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveBinderKey() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseBinderKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveEarlyTrafficSecret() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseEarlyTrafficSecret() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] computePskBinder(byte[] clientHello, int length) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] computePskBinder(ByteBuffer[] clientHello) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] computePsk(byte[] ticketNonce) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private SecretKey createKey(byte[] key) {
        SecretKey secretKey = cipherSuiteSpec.getAead().createKey(key);
        Arrays.fill(key, (byte) 0);
        return secretKey;
    }

    public TrafficKeys deriveEarlyTrafficKeys() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TrafficKeys deriveEarlyTrafficKeys(byte[] keyLabel, byte[] ivLabel) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DerivedSecrets deriveEarlySecrets(byte[] label, int length) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveHandshakeSecret(byte[] sharedSecret) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseHandshakeSecret() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveHandshakeTrafficSecrets() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] computeServerVerifyData() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] computeClientVerifyData() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseHandshakeTrafficSecrets() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TrafficKeys deriveHandshakeTrafficKeys() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TrafficKeys deriveHandshakeTrafficKeys(byte[] keyLabel, byte[] ivLabel) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DerivedSecrets deriveHandshakeSecrets(byte[] label, int length) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveMasterSecret() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseMasterSecret() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void deriveApplicationTrafficSecrets() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseApplicationTrafficSecrets() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void eraseApplicationTrafficSecret(boolean client) {
        if (client) {
            if (clientApplicationTrafficSecret != null) {
                Arrays.fill(clientApplicationTrafficSecret, (byte) 0);
                clientApplicationTrafficSecret = null;
            }
        } else {
            if (serverApplicationTrafficSecret != null) {
                Arrays.fill(serverApplicationTrafficSecret, (byte) 0);
                serverApplicationTrafficSecret = null;
            }
        }
    }

    public void deriveResumptionMasterSecret() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void eraseResumptionMasterSecret() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TrafficKeys deriveApplicationTrafficKeys() throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TrafficKeys deriveApplicationTrafficKeys(byte[] keyLabel, byte[] ivLabel) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DerivedSecrets deriveApplicationSecrets(byte[] label, int length) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TrafficKeys deriveNextGenerationTrafficKey(boolean client) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void eraseAll() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
