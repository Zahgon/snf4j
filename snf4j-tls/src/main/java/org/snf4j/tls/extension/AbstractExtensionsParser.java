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
import java.util.LinkedList;
import java.util.List;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.DecodeErrorAlert;
import org.snf4j.tls.handshake.HandshakeType;

public abstract class AbstractExtensionsParser implements IExtensionsParser {

    private List<IExtension> extensions = new LinkedList<IExtension>();

    private int remaining = -1;

    private int consumed = 0;

    private final int minLength;

    private final int maxLength;

    protected AbstractExtensionsParser(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getConsumedBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isComplete() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<IExtension> getExtensions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void parse(HandshakeType handshakeType, ByteBuffer[] srcs, int remainig) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void parse(HandshakeType handshakeType, ByteBufferArray srcs, int remaining) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected abstract IExtension parseExtension(HandshakeType handshakeType, ByteBufferArray srcs, int remaining) throws Alert;
}
