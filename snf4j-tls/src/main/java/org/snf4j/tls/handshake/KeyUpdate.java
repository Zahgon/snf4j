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
package org.snf4j.tls.handshake;

import java.nio.ByteBuffer;
import java.util.List;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.Args;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.extension.IExtension;
import org.snf4j.tls.extension.IExtensionDecoder;

public class KeyUpdate extends KnownHandshake implements IKeyUpdate {

    private final static HandshakeType TYPE = HandshakeType.KEY_UPDATE;

    private final KeyUpdateRequest request;

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

    public KeyUpdate(KeyUpdateRequest request) {
        super(TYPE);
        Args.checkNull(request, "request");
        this.request = request;
    }

    public KeyUpdate(boolean requested) {
        super(TYPE);
        request = requested ? KeyUpdateRequest.UPDATE_REQUESTED : KeyUpdateRequest.UPDATE_NOT_REQUESTED;
    }

    public static IHandshakeParser getParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getDataLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<IExtension> getExtensions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void getData(ByteBuffer buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public KeyUpdateRequest getRequest() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
