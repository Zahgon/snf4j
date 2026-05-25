/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2019 SNF4J contributors
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
package org.snf4j.example.engine;

import java.nio.ByteBuffer;
import org.snf4j.core.engine.EngineResult;
import org.snf4j.core.engine.IEngineResult;
import org.snf4j.core.engine.Status;

public class BasicEngine extends AbstractEngine {

    protected int offset;

    private boolean finished;

    public BasicEngine(int offset) {
        this.offset = offset;
    }

    protected IEngineResult closeWrap(ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected IEngineResult preWrap(ByteBuffer[] srcs, ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IEngineResult wrap(ByteBuffer[] srcs, ByteBuffer dst) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected IEngineResult closeUnwrap(ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isClosingUnwrap(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected IEngineResult preUnwrap(ByteBuffer src, ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IEngineResult unwrap(ByteBuffer src, ByteBuffer dst) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
