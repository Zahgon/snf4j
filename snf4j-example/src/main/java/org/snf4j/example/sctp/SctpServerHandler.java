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
package org.snf4j.example.sctp;

import java.nio.ByteBuffer;
import java.util.Random;
import org.snf4j.core.ImmutableSctpMessageInfo;
import org.snf4j.core.allocator.IByteBufferAllocator;
import org.snf4j.core.allocator.ThreadLocalCachingAllocator;
import org.snf4j.core.factory.DefaultSessionStructureFactory;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.AbstractSctpHandler;
import org.snf4j.core.session.ISctpSessionConfig;
import com.sun.nio.sctp.MessageInfo;

public class SctpServerHandler extends AbstractSctpHandler {

    final static IByteBufferAllocator ALLOCATOR = new ThreadLocalCachingAllocator(true);

    final static ImmutableSctpMessageInfo COMPRESSED = ImmutableSctpMessageInfo.create(1);

    final static ImmutableSctpMessageInfo ENCODED = ImmutableSctpMessageInfo.create(1, 0, true);

    final static ImmutableSctpMessageInfo OTHER = ImmutableSctpMessageInfo.create(0);

    final static ImmutableSctpMessageInfo[] STREAMS = new ImmutableSctpMessageInfo[] { COMPRESSED, ENCODED, OTHER };

    final static Random RANDOM = new Random(System.currentTimeMillis());

    ImmutableSctpMessageInfo randomStream() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ByteBuffer msg() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void check(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg, MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISctpSessionConfig getConfig() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionStructureFactory getFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
