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

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;

class DatagramChannelContext extends SessionChannelContext<DatagramSession> {

    DatagramChannelContext(DatagramSession session) {
        super(session);
    }

    @Override
    final void close(SelectableChannel channel) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final String toString(SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final void handle(final SelectorLoop loop, final SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final ChannelContext<DatagramSession> wrap(InternalSession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final void shutdown(SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final boolean exceptionOnDecodingFailure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
