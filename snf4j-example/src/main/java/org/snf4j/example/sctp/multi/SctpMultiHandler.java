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
package org.snf4j.example.sctp.multi;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Set;
import org.snf4j.core.ImmutableSctpMessageInfo;
import org.snf4j.core.allocator.IByteBufferAllocator;
import org.snf4j.core.allocator.ThreadLocalCachingAllocator;
import org.snf4j.core.factory.DefaultSessionStructureFactory;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.AbstractSctpHandler;
import org.snf4j.core.handler.SctpNotificationType;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.DefaultSctpSessionConfig;
import org.snf4j.core.session.ISctpMultiSession;
import org.snf4j.core.session.ISctpSessionConfig;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.Notification;
import com.sun.nio.sctp.SendFailedNotification;
import com.sun.nio.sctp.ShutdownNotification;

class SctpMultiHandler extends AbstractSctpHandler {

    final static IByteBufferAllocator ALLOCATOR = new ThreadLocalCachingAllocator(true);

    final private AssociationManager associations;

    SctpMultiHandler(SocketAddress... peers) {
        associations = new AssociationManager(this, Server.MAX_COUNT, peers);
    }

    @Override
    public ISctpMultiSession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ImmutableSctpMessageInfo immutableMsgInfo(MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg, MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ByteBuffer initialMsg() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String addresses(Set<SocketAddress> addresses) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String prefix(Notification n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    void notification(AssociationChangeNotification n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void notification(SendFailedNotification n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void notification(ShutdownNotification n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public HandlerResult notification(Notification notification, SctpNotificationType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void log(String msg) {
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
