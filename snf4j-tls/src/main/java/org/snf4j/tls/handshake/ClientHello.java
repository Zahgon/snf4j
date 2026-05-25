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
package org.snf4j.tls.handshake;

import java.nio.ByteBuffer;
import java.util.List;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.Args;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.extension.ExtensionsParser;
import org.snf4j.tls.extension.ExtensionsUtil;
import org.snf4j.tls.extension.IExtension;
import org.snf4j.tls.extension.IExtensionDecoder;

public class ClientHello extends AbstractHello implements IClientHello {

    private final static HandshakeType TYPE = HandshakeType.CLIENT_HELLO;

    private final CipherSuite[] cipherSuites;

    private final byte[] legacyCompressionMethods;

    private final List<IExtension> extensions;

    private final static AbstractHandshakeParser PARSER = new AbstractHandshakeParser() {

        @Override
        public HandshakeType getType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IHandshake parse(ByteBufferArray srcs, int remaining, IExtensionDecoder decoder) throws Alert {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    public ClientHello(int legacyVersion, byte[] random, byte[] legacySessionId, CipherSuite[] cipherSuites, byte[] legacyCompressionMethods, List<IExtension> extensions) {
        super(TYPE, legacyVersion, random, legacySessionId);
        Args.checkNull(cipherSuites, "cipherSuites");
        Args.checkNull(extensions, "extensions");
        Args.checkMax(legacyCompressionMethods, 255, "legacyCompressionMethods");
        this.cipherSuites = cipherSuites;
        this.legacyCompressionMethods = legacyCompressionMethods;
        this.extensions = extensions;
    }

    @Override
    public CipherSuite[] getCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] getLegacyCompressionMethods() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<IExtension> getExtensions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static IHandshakeParser getParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getDataLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void getData(ByteBuffer buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
