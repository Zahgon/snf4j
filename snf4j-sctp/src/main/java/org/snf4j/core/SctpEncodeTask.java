/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2021-2022 SNF4J contributors
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
package org.snf4j.core;

import java.nio.ByteBuffer;
import java.util.List;
import org.snf4j.core.future.IFuture;

class SctpEncodeTask extends EncodeTask {

    ImmutableSctpMessageInfo msgInfo;

    private ISctpEncodeTaskWriter writer;

    SctpEncodeTask(InternalSession session, byte[] bytes) {
        super(session, bytes);
    }

    SctpEncodeTask(InternalSession session, byte[] bytes, int offset, int length) {
        super(session, bytes, offset, length);
    }

    SctpEncodeTask(InternalSession session, ByteBuffer buffer) {
        super(session, buffer);
    }

    SctpEncodeTask(InternalSession session, ByteBuffer buffer, int length) {
        super(session, buffer, length);
    }

    SctpEncodeTask(InternalSession session, Object msg) {
        super(session, msg);
    }

    final void registernf(ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IFuture<Void> register(ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void setWriter(IEncodeTaskWriter writer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected IFuture<Void> write(byte[] data, boolean withFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected IFuture<Void> write(ByteBuffer data, boolean withFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected IFuture<Void> write(IByteBufferHolder data, boolean withFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected List<Object> encode(Object msg) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected List<Object> encode(byte[] bytes) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected List<Object> encode(ByteBuffer buffer) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
