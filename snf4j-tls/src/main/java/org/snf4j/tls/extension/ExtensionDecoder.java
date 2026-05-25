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

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.DecodeErrorAlert;
import org.snf4j.tls.handshake.HandshakeType;

public class ExtensionDecoder implements IExtensionDecoder {

    public final static IExtensionDecoder DEFAULT;

    static {
        ExtensionDecoder decoder = new ExtensionDecoder();
        decoder.addParser(ServerNameExtension.getParser());
        decoder.addParser(SupportedVersionsExtension.getParser());
        decoder.addParser(SupportedGroupsExtension.getParser());
        decoder.addParser(KeyShareExtension.getParser());
        decoder.addParser(SignatureAlgorithmsExtension.getParser());
        decoder.addParser(SignatureAlgorithmsCertExtension.getParser());
        decoder.addParser(CookieExtension.getParser());
        decoder.addParser(PreSharedKeyExtension.getParser());
        decoder.addParser(PskKeyExchangeModesExtension.getParser());
        decoder.addParser(EarlyDataExtension.getParser());
        decoder.addParser(ALPNExtension.getParser());
        DEFAULT = decoder;
    }

    private final Map<ExtensionType, IExtensionParser> parsers = new HashMap<ExtensionType, IExtensionParser>();

    protected ExtensionType getType(int type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addParser(IExtensionParser parser) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IExtensionParser removeParser(ExtensionType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasParser(ExtensionType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<IExtensionParser> getParsers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearParsers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IExtension decode(HandshakeType handshakeType, ByteBuffer[] srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IExtension decode(HandshakeType handshakeType, ByteBufferArray srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
