/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2021-2022 SNF4J contributors
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
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.snf4j.core.future.CancelledFuture;
import org.snf4j.core.future.FailedFuture;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.SuccessfulFuture;
import org.snf4j.core.future.TaskFuture;
import org.snf4j.core.handler.ISctpHandler;
import org.snf4j.core.handler.SctpNotificationType;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.logger.LoggerFactory;
import org.snf4j.core.session.ISctpMultiSession;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.AssociationChangeNotification.AssocChangeEvent;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.Notification;
import com.sun.nio.sctp.SctpMultiChannel;

/**
 * The Stream Control Transmission Protocol (SCTP) multi-session.
 * <p>
 * It uses the message-oriented connected SCTP socket as described in the IETF
 * RFC 4960 "Stream Control Transmission Protocol".
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class SctpMultiSession extends InternalSctpSession implements ISctpMultiSession {

    private final static ILogger LOGGER = LoggerFactory.getLogger(SctpMultiSession.class);

    private Set<Association> shutdowns;

    private Set<Association> pendingShutdowns;

    SctpMultiSession(String name, ISctpHandler handler) {
        super(name, handler, LOGGER);
    }

    SctpMultiSession(ISctpHandler handler) {
        this(null, handler);
    }

    @Override
    SessionPipeline<?> createPipeline() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISctpMultiSession getParent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> shutdown(final Association association) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Association getAssociation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Association> getAssociations() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    HandlerResult notification(Notification notification, SctpNotificationType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    boolean closeNow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Always run in the loop's thread
     */
    void shutdown(Set<Association> associations) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean shutdown(SctpMultiChannel channel) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketAddress getRemoteAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<SocketAddress> getRemoteAddresses() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<SocketAddress> getRemoteAddresses(Association association) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    Set<SocketAddress> getAddresses(Association association, boolean local) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void bind(InetAddress address) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void unbind(InetAddress address) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void checkConfig() {
        if (!defaultPeerAddress)
            throw new IllegalStateException("default peer address is not configured");
    }

    @Override
    IFuture<Void> writeFuture(long expectedLen) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] msg, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] msg, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer msg, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer msg, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
