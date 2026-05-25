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

import java.util.List;
import org.snf4j.core.codec.IDecoder;
import org.snf4j.core.session.ISession;
import org.snf4j.core.session.IStreamSession;

/**
 * Validates the UTF-8 encoding correctness of the payload data in Web Socket
 * text frames and in Web Socket continuation frames being theirs continuation.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class FrameUtf8Validator implements IDecoder<Frame, Frame> {

    private Utf8.ValidationContext context;

    @Override
    public Class<Frame> getInboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Class<Frame> getOutboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private RuntimeException nonUtf8(ISession session) throws InvalidFrameException {
        ((IStreamSession) session).writenf(new CloseFrame(CloseFrame.NON_UTF8));
        return new InvalidFrameException("Invalid text frame payload: bytes are not UTF-8");
    }

    @Override
    public void decode(ISession session, Frame frame, List<Frame> out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
