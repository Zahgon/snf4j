/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022 SNF4J contributors
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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.snf4j.tls.Args;

public class Hkdf implements IHkdf {

    private final Mac mac;

    public Hkdf(Mac mac) {
        Args.checkNull(mac, "mac");
        this.mac = mac;
    }

    @Override
    public byte[] extract(Key salt, byte[] ikm) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] extract(byte[] salt, byte[] ikm) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] extract(byte[] ikm) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getAlgorithm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Mac getMacFuntion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getMacLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] expand(Key prk, byte[] info, int length) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] expand(byte[] prk, byte[] info, int length) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] expand(byte[] info, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
