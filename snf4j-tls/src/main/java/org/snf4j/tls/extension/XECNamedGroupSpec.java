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
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.crypto.IKeyExchange;
import org.snf4j.tls.crypto.IXDHKeyExchange;
import org.snf4j.tls.crypto.XDHKeyExchange;

public class XECNamedGroupSpec extends AbstractNamedGroupSpec {

    public final static XECNamedGroupSpec X25519 = new XECNamedGroupSpec(XDHKeyExchange.X25519, 32);

    public final static XECNamedGroupSpec X448 = new XECNamedGroupSpec(XDHKeyExchange.X448, 56);

    private final int contentLength;

    private final IXDHKeyExchange keyExchange;

    @Override
    public boolean isImplemented() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public XECNamedGroupSpec(IXDHKeyExchange keyExchange, int contentLength) {
        this.contentLength = contentLength;
        this.keyExchange = keyExchange;
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

    void getData(ByteBuffer buffer, byte[] u) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IKeyExchange getKeyExchange() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void reverse(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private class XECParsedKey implements ParsedKey {

        private final byte[] u;

        XECParsedKey(byte[] u) {
            this.u = u;
        }

        public byte[] getU() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
