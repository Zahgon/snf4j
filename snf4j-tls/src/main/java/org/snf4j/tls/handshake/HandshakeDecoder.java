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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.DecodeErrorAlert;
import org.snf4j.tls.extension.ExtensionDecoder;
import org.snf4j.tls.extension.IExtensionDecoder;

public class HandshakeDecoder implements IHandshakeDecoder {

    public final static IHandshakeDecoder DEFAULT;

    static {
        HandshakeDecoder decoder = new HandshakeDecoder(ExtensionDecoder.DEFAULT);
        decoder.addParser(ClientHello.getParser());
        decoder.addParser(ServerHello.getParser());
        decoder.addParser(EncryptedExtensions.getParser());
        decoder.addParser(Certificate.getParser());
        decoder.addParser(CertificateVerify.getParser());
        decoder.addParser(CertificateRequest.getParser());
        decoder.addParser(Finished.getParser());
        decoder.addParser(NewSessionTicket.getParser());
        decoder.addParser(KeyUpdate.getParser());
        decoder.addParser(EndOfEarlyData.getParser());
        DEFAULT = decoder;
    }

    private final Map<HandshakeType, IHandshakeParser> parsers = new HashMap<HandshakeType, IHandshakeParser>();

    private IExtensionDecoder extensionDecoder;

    public HandshakeDecoder(IExtensionDecoder extensionDecoder) {
        this.extensionDecoder = extensionDecoder;
    }

    protected HandshakeType getType(int type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addParser(IHandshakeParser parser) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IHandshakeParser removeParser(HandshakeType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasParser(HandshakeType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<IHandshakeParser> getParsers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearParsers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IHandshake decode(ByteBuffer[] srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IHandshake decode(ByteBufferArray srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
