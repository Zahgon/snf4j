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
package org.snf4j.core;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.snf4j.core.allocator.IByteBufferAllocator;

class SctpFragments {

    enum State {

        EMPTY, SINGLE, MULTI
    }

    private State state = State.EMPTY;

    private ByteBuffer fragment;

    private long fragmentKey = -1;

    private Map<Long, ByteBuffer> fragments;

    private final IByteBufferAllocator allocator;

    private final int minCapacity;

    private final int maxCapacity;

    private final boolean optimize;

    private final boolean release;

    SctpFragments(IByteBufferAllocator allocator, int minCapacity, int maxCapacity, boolean optimize) {
        this.allocator = allocator;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.optimize = optimize;
        release = allocator.isReleasable();
    }

    /**
     * Stores incomplete buffer
     *
     * @param fragmentKey stream number
     * @param msg input buffer (not flipped yet)
     * @return input buffer (can be null)
     */
    ByteBuffer store(long fragmentKey, ByteBuffer msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns complete buffer
     *
     * @param fragmentKey stream number
     * @param msg          input buffer (not flipped yet)
     * @return complete buffer (not flipped yet)
     */
    ByteBuffer complete(long fragmentKey, ByteBuffer msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void release() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
