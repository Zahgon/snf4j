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
package org.snf4j.tls.extension;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.crypto.ECKeyExchange;
import org.snf4j.tls.crypto.IECKeyExchange;
import org.snf4j.tls.crypto.IKeyExchange;

public class ECNamedGroupSpec extends AbstractNamedGroupSpec {

    public final static ECNamedGroupSpec SECP256R1 = new ECNamedGroupSpec(ECKeyExchange.SECP256R1, 32);

    public final static ECNamedGroupSpec SECP384R1 = new ECNamedGroupSpec(ECKeyExchange.SECP384R1, 48);

    public final static ECNamedGroupSpec SECP521R1 = new ECNamedGroupSpec(ECKeyExchange.SECP521R1, 66);

    private final int coordinateLength;

    private final IECKeyExchange keyExchange;

    public ECNamedGroupSpec(IECKeyExchange keyExchange, int coordinateLength) {
        this.coordinateLength = coordinateLength;
        this.keyExchange = keyExchange;
    }

    @Override
    public boolean isImplemented() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IKeyExchange getKeyExchange() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ParsedKey parse(ByteBufferArray srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public PublicKey generateKey(ParsedKey key) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getDataLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void getData(ByteBuffer buffer, PublicKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void getData(ByteBuffer buffer, ParsedKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void getData(ByteBuffer buffer, byte[] x, byte[] y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private class ECParsedKey implements ParsedKey {

        private final byte[] x;

        private final byte[] y;

        ECParsedKey(byte[] x, byte[] y) {
            this.x = x;
            this.y = y;
        }

        public byte[] getX() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public byte[] getY() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
