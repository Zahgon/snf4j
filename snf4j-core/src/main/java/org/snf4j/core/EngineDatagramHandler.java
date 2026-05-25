/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020-2024 SNF4J contributors
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

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import org.snf4j.core.EngineDatagramWrapper.EngineDatagramRecord;
import org.snf4j.core.engine.HandshakeStatus;
import org.snf4j.core.engine.IEngine;
import org.snf4j.core.engine.IEngineResult;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.ITwoThresholdFuture;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.IDatagramHandler;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.session.IDatagramSession;
import org.snf4j.core.session.IEngineSession;
import org.snf4j.core.session.ISession;
import org.snf4j.core.session.ISessionTimer;
import org.snf4j.core.timer.DefaultTimeoutModel;
import org.snf4j.core.timer.ITimeoutModel;
import org.snf4j.core.timer.ITimerTask;

class EngineDatagramHandler extends AbstractEngineHandler<DatagramSession, IDatagramHandler> implements IDatagramHandler {

    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.wrap(new byte[0]);

    private Queue<EngineDatagramRecord> outAppBuffers;

    private ByteBuffer inAppBuffer;

    private Queue<ByteBuffer> inNetBuffers;

    private final SocketAddress remoteAddress;

    private ITimerTask retransmissionTimer;

    private final ITimeoutModel timeoutModel;

    private static final Object RETRANSMISSION_TIMEOUT_EVENT = new Object();

    private static final boolean[] RETRANSMISSION_RESET = new boolean[HandshakeStatus.values().length];

    static {
        RETRANSMISSION_RESET[HandshakeStatus.NEED_WRAP.ordinal()] = true;
        RETRANSMISSION_RESET[HandshakeStatus.NEED_TASK.ordinal()] = true;
        RETRANSMISSION_RESET[HandshakeStatus.FINISHED.ordinal()] = true;
        RETRANSMISSION_RESET[HandshakeStatus.NOT_HANDSHAKING.ordinal()] = true;
    }

    EngineDatagramHandler(IEngine engine, SocketAddress remoteAddress, IDatagramHandler handler, ILogger logger) {
        super(engine, handler, logger);
        this.remoteAddress = remoteAddress;
        ITimeoutModel model = handler.getFactory().getTimeoutModel();
        timeoutModel = model != null ? model : new DefaultTimeoutModel();
    }

    private final void scheduleRetransmission() {
        if (!timeoutModel.isEnabled()) {
            return;
        }
        ISessionTimer timer = session.getTimer();
        if (retransmissionTimer == null && timer.isSupported()) {
            long timeout = timeoutModel.next();
            retransmissionTimer = timer.scheduleEvent(RETRANSMISSION_TIMEOUT_EVENT, timeout);
            if (traceEnabled) {
                logger.trace("Retransmission timer scheduled for execution after {} ms for {}", timeout, session);
            }
        }
    }

    private final void cancelRetransmissionTimer() {
        if (retransmissionTimer != null) {
            retransmissionTimer.cancelTask();
            retransmissionTimer = null;
            if (traceEnabled) {
                logger.trace("Retransmission timer canceled for {}", session);
            }
        }
    }

    @Override
    void handleClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void timer(Object event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(SocketAddress remoteAddress, byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(SocketAddress remoteAddress, ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    boolean handleClosing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void pollInNetBuffers() {
        ByteBuffer b = inNetBuffers.poll();
        if (session.optimizeBuffers) {
            allocator.release(b);
        }
    }

    private final void tryReleaseInAppBuffer() {
        if (session.optimizeBuffers) {
            allocator.release(inAppBuffer);
            inAppBuffer = null;
        }
    }

    @Override
    boolean unwrap(HandshakeStatus[] status) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    boolean wrap(HandshakeStatus[] status) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    Executor getExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    boolean needUnwrap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void superQuickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void sessionClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void preCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void releaseBuffers(Queue<EngineDatagramRecord> records) {
        EngineDatagramRecord record;
        while ((record = records.poll()) != null) {
            if (record.release) {
                for (ByteBuffer buffer : record.holder.toArray()) {
                    allocator.release(buffer);
                }
                record.holder = null;
            }
        }
    }

    @Override
    void postEnding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSession(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IDatagramSession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IDatagramHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final boolean flush(EngineDatagramRecord record, ByteBuffer buffer) {
        if (buffer.position() == 0) {
            if (allocator.isReleasable()) {
                allocator.release(buffer);
            }
            return false;
        } else if (record == null) {
            record = new EngineDatagramRecord(null);
        }
        buffer.flip();
        record.holder = new SingleByteBufferHolder(buffer);
        record.release = true;
        long futureThreshold = session.superWrite(record);
        if (record.future != null) {
            record.future.setSecondThreshold(futureThreshold);
        }
        return true;
    }

    IFuture<Void> write(EngineDatagramRecord record, boolean needFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(SocketAddress remoteAddress, Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(SocketAddress remoteAddress, DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
