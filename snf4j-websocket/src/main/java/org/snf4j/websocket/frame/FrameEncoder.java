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
package org.snf4j.websocket.frame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Random;
import org.snf4j.core.codec.IEncoder;
import org.snf4j.core.session.ISession;

/**
 * Encodes a Web Socket frame into bytes in the protocol version 13 format.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class FrameEncoder implements IEncoder<Frame, ByteBuffer> {

    private final static Random RANDOM = new Random();

    private final boolean clientMode;

    private boolean closed;

    /**
     * Constructs a Web Socket encoder.
     *
     * @param clientMode determines the mode (client/server) in which the encoder
     *                   should work
     */
    public FrameEncoder(boolean clientMode) {
        this.clientMode = clientMode;
    }

    @Override
    public Class<Frame> getInboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Class<ByteBuffer> getOutboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void encode(ISession session, Frame frame, List<ByteBuffer> out) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int length(Frame frame) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
