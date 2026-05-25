/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022-2023 SNF4J contributors
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

import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

public class RSASSAPSSSignature implements ISignature {

    private final static String RSA = "RSA";

    private final static String RSASSA_PSS = "RSASSA-PSS";

    public final static RSASSAPSSSignature RSA_PSS_RSAE_SHA256 = new RSASSAPSSSignature(RSASSA_PSS, RSA, 528, "SHA-256", 32);

    public final static RSASSAPSSSignature RSA_PSS_RSAE_SHA384 = new RSASSAPSSSignature(RSASSA_PSS, RSA, 784, "SHA-384", 48);

    public final static RSASSAPSSSignature RSA_PSS_RSAE_SHA512 = new RSASSAPSSSignature(RSASSA_PSS, RSA, 1040, "SHA-512", 64);

    public final static RSASSAPSSSignature RSA_PSS_PSS_SHA256 = new RSASSAPSSSignature(RSASSA_PSS, RSASSA_PSS, 528, "SHA-256", 32);

    public final static RSASSAPSSSignature RSA_PSS_PSS_SHA384 = new RSASSAPSSSignature(RSASSA_PSS, RSASSA_PSS, 784, "SHA-384", 48);

    public final static RSASSAPSSSignature RSA_PSS_PSS_SHA512 = new RSASSAPSSSignature(RSASSA_PSS, RSASSA_PSS, 1040, "SHA-512", 64);

    private final static byte[] HASH_ALGORITHM_PREFIX = { -96, 13, 48, 11, 6, 9, 96, -122, 72, 1, 101, 3, 4, 2 };

    private final static byte SHA256 = 1;

    private final static byte SHA384 = 2;

    private final static byte SHA512 = 3;

    private final String hashName;

    private final String algorithm;

    private final String keyAlgorithm;

    private final boolean ssaPssKey;

    private final int minKeySize;

    private final int saltLength;

    private final static boolean IMPLEMENTED;

    private final static Method RSA_KEY_GET_PARAMS;

    static boolean implemented(String algorithm) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Method method(String className, String method) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static {
        IMPLEMENTED = implemented(RSASSA_PSS);
        RSA_KEY_GET_PARAMS = method(RSAKey.class.getName(), "getParams");
    }

    public RSASSAPSSSignature(String algorithm, String keyAlgorithm, int minKeySize, String hashName, int saltLength) {
        this.algorithm = algorithm;
        this.keyAlgorithm = keyAlgorithm;
        this.ssaPssKey = RSASSA_PSS.equals(keyAlgorithm);
        this.minKeySize = minKeySize;
        this.hashName = hashName;
        this.saltLength = saltLength;
    }

    AlgorithmParameterSpec createParameter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Signature createSignature(String algorithm) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Signature createSignature() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String algorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String keyAlgorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean matches(X509Certificate cert) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean matchesByKey(X509Certificate cert) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean certMatches(X509Certificate cert, byte[] pssParams, Method params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean keyMatches(X509Certificate cert, Key key, Method params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private int indexAfterPrefix(byte[] src, byte[] prefix) {
        if (prefix.length >= src.length) {
            return -1;
        }
        int len = src.length - prefix.length + 1;
        for (int i = 0; i < len; ++i) {
            boolean found = true;
            int j = 0;
            for (; j < prefix.length; ++j) {
                if (src[i + j] != prefix[j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return i + j;
            }
        }
        return -1;
    }

    boolean pssParamsMatches(byte[] pssParams) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean keyMatches(Key key, Method params) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int minKeySize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isImplemented() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
