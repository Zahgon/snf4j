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
import java.nio.channels.SelectionKey;
import java.util.Queue;
import org.snf4j.core.InternalSctpSession.SctpRecord;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.SctpNotificationType;
import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.Notification;
import com.sun.nio.sctp.NotificationHandler;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import com.sun.nio.sctp.SendFailedNotification;
import com.sun.nio.sctp.ShutdownNotification;

abstract class AbstractSctpChannelContext<T extends InternalSctpSession> extends SessionChannelContext<T> {

    AbstractSctpChannelContext(T session) {
        super(session);
    }

    @Override
    final void handle(final SelectorLoop loop, final SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final static AbstractNotificationHandler<InternalSctpSession> HANDLER = new AbstractNotificationHandler<InternalSctpSession>() {

        @Override
        public HandlerResult handleNotification(PeerAddressChangeNotification notification, InternalSctpSession session) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public HandlerResult handleNotification(AssociationChangeNotification notification, InternalSctpSession session) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public HandlerResult handleNotification(Notification notification, InternalSctpSession session) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public HandlerResult handleNotification(SendFailedNotification notification, InternalSctpSession session) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public HandlerResult handleNotification(ShutdownNotification notification, InternalSctpSession session) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    abstract MessageInfo receive(SelectionKey key, ByteBuffer msg, final T session, NotificationHandler<InternalSctpSession> handler) throws Exception;

    void handleReading(final SelectorLoop loop, final T session, final SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract int send(SelectionKey key, ByteBuffer msg, MessageInfo msgInfo) throws Exception;

    int send(T session, SelectionKey key, SctpRecord record) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int handleWriting(final SelectorLoop loop, final T session, final SelectionKey key, int spinCount) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final boolean exceptionOnDecodingFailure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
