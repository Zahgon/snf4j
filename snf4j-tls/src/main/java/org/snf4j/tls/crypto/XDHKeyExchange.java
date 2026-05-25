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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.KeyAgreement;

public class XDHKeyExchange implements IXDHKeyExchange {

    public final static XDHKeyExchange X25519 = new XDHKeyExchange("X25519");

    public final static XDHKeyExchange X448 = new XDHKeyExchange("X448");

    private final static String PARAMETER_SPEC_NAME = "java.security.spec.NamedParameterSpec";

    private final static String KEY_SPEC_NAME = "java.security.spec.XECPublicKeySpec";

    private final static String KEY_NAME = "java.security.interfaces.XECPublicKey";

    private final static boolean IMPLEMENTED;

    private final static Constructor<?> PARAMETER_SPEC;

    private final static Constructor<?> KEY_SPEC;

    private final static Method GET_U;

    static Constructor<?> constructor(String clazzName, Class<?>... argTypes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Method method(String clazzName, String name, Class<?>... argTypes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean implemented(String clazzName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static {
        PARAMETER_SPEC = constructor(PARAMETER_SPEC_NAME, String.class);
        KEY_SPEC = constructor(KEY_SPEC_NAME, AlgorithmParameterSpec.class, BigInteger.class);
        GET_U = method(KEY_NAME, "getU");
        IMPLEMENTED = implemented(KEY_NAME);
    }

    private final String dh;

    private final String algorithm;

    public XDHKeyExchange(String algorithm) {
        this("XDH", algorithm);
    }

    XDHKeyExchange(String dh, String algorithm) {
        this.algorithm = algorithm;
        this.dh = dh;
    }

    @Override
    public String getAlgorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isImplemented() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] generateSecret(PrivateKey privateKey, PublicKey publicKey, SecureRandom random) throws NoSuchAlgorithmException, InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    AlgorithmParameterSpec paramSpec(Constructor<?> paramSpec) throws NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public KeyPair generateKeyPair(SecureRandom random) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    KeySpec keySpec(Constructor<?> paramSpec, Constructor<?> keySpec, BigInteger u) throws NoSuchAlgorithmException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public PublicKey generatePublicKey(BigInteger u) throws NoSuchAlgorithmException, InvalidKeySpecException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    BigInteger getU(Method getU, PublicKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public BigInteger getU(PublicKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
