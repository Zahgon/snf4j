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
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import org.snf4j.core.InternalSctpSession.SctpRecord;
import org.snf4j.core.future.IAbortableFuture;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.handler.SctpSendingFailureException;
import org.snf4j.core.handler.SessionIncident;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.NotificationHandler;
import com.sun.nio.sctp.SctpMultiChannel;

class SctpMultiChannelContext extends AbstractSctpChannelContext<SctpMultiSession> {

    SctpMultiChannelContext(SctpMultiSession session) {
        super(session);
    }

    @Override
    MessageInfo receive(SelectionKey key, ByteBuffer msg, SctpMultiSession session, NotificationHandler<InternalSctpSession> handler) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    int send(SelectionKey key, ByteBuffer msg, MessageInfo msgInfo) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    ChannelContext<SctpMultiSession> wrap(InternalSession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void shutdown(SelectableChannel channel) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    int send(SctpMultiSession session, SelectionKey key, SctpRecord record) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String toString(SctpMultiChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final String toString(SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
