/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2021 SNF4J contributors
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
package org.snf4j.websocket.extensions.compress;

import java.nio.ByteBuffer;
import java.util.List;
import org.snf4j.core.codec.ICodec;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.codec.IEventDrivenCodec;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.ISession;
import org.snf4j.websocket.frame.BinaryFrame;
import org.snf4j.websocket.frame.ContinuationFrame;
import org.snf4j.websocket.frame.Frame;
import org.snf4j.websocket.frame.TextFrame;

abstract class DeflateCodec implements ICodec<Frame, Frame>, IEventDrivenCodec {

    static final byte[] TAIL = new byte[] { 0, 0, -1, -1 };

    @Override
    public Class<Frame> getInboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Class<Frame> getOutboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract int rsvBits(Frame frame);

    abstract IEventDrivenCodec codec();

    static byte[] bytes(List<ByteBuffer> bufs, boolean removeTail) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    Frame createFrame(Frame frame, byte[] payload) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void added(ISession session, ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(ISession session, SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void removed(ISession session, ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
