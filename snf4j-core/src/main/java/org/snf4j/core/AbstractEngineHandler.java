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

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.snf4j.core.allocator.IByteBufferAllocator;
import org.snf4j.core.engine.HandshakeStatus;
import org.snf4j.core.engine.IEngine;
import org.snf4j.core.engine.IEngineTimerTask;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.HandshakeLoopsThresholdException;
import org.snf4j.core.handler.HandshakeTimeoutException;
import org.snf4j.core.handler.IAllocatingHandler;
import org.snf4j.core.handler.IHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.handler.SessionIncidentException;
import org.snf4j.core.logger.ExceptionLogger;
import org.snf4j.core.logger.IExceptionLogger;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.session.ISessionTimer;
import org.snf4j.core.timer.ITimerTask;

abstract class AbstractEngineHandler<S extends InternalSession, H extends IHandler> implements IHandler, Runnable, IAllocatingHandler {

    private final static AtomicLong nextDelegatedTaskId = new AtomicLong(0);

    private final static int MAX_HANDSHAKE_LOOPS_THRESHOLD = Integer.getInteger(Constants.MAX_HANDSHAKE_LOOPS_THRESHOLD, 500);

    private static final Object HANDSHAKE_TIMEOUT_EVENT = new Object();

    final ILogger logger;

    final IExceptionLogger elogger = ExceptionLogger.getInstance();

    S session;

    final IEngine engine;

    final H handler;

    volatile int minAppBufferSize;

    int minNetBufferSize;

    final IByteBufferAllocator allocator;

    final Object writeLock = new Object();

    volatile ClosingState closing = ClosingState.NONE;

    /**
     * Counts total application bytes that was already wrapped
     */
    long netCounter;

    /**
     * Counts total application bytes that needed wrapping
     */
    volatile long appCounter;

    /**
     * Tells if the initial handshaking is pending
     */
    boolean isReadyPending = true;

    /**
     * Tells if any incoming data is ignored
     */
    boolean readIgnored;

    boolean handshaking;

    private ITimerTask handshakeTimer;

    int handshakeLoops;

    enum Handshake {

        NONE, REQUESTED, STARTED
    }

    final AtomicReference<Handshake> handshake = new AtomicReference<Handshake>(Handshake.NONE);

    boolean debugEnabled;

    boolean traceEnabled;

    /**
     * Delayed exception causing a gentle close so any pending data (e.g. Alert) can
     * be still wrapped and send back after reporting the real cause to the handler.
     */
    DelayedException delayedException;

    boolean delayedCloseNeeded;

    AbstractEngineHandler(IEngine engine, H handler, ILogger logger) {
        this.engine = engine;
        this.handler = handler;
        this.logger = logger;
        allocator = handler.getFactory().getAllocator();
    }

    boolean tryDelayedException(Throwable t, HandshakeStatus[] status) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean fireDelayedException() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return true if wrap needed
     */
    abstract boolean handleClosing();

    /**
     * Returns false when the processing loop need to be broken
     */
    abstract boolean unwrap(HandshakeStatus[] status);

    /**
     * Returns false when the processing loop need to be broken
     */
    abstract boolean wrap(HandshakeStatus[] status);

    abstract Executor getExecutor();

    abstract boolean needUnwrap();

    /**
     * Quickly closes super session
     */
    abstract void superQuickClose();

    /**
     * Method is always running in the same selector loop's thread
     */
    @Override
    public void run() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    void run(HandshakeStatus[] status) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleOpened() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Object getEngineSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleBeginHandshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void cancelHandshakeTimer() {
        if (handshakeTimer != null) {
            handshakeTimer.cancelTask();
            handshakeTimer = null;
            if (traceEnabled) {
                logger.trace("Handshake expiration timer canceled for {}", session);
            }
        }
    }

    void handleFinished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleReady() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void closeOutbound() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void preCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void postEnding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void beginHandshake(boolean lazy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireReady() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireException(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void quickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void dirtyClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IByteBufferAllocator getAllocator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean incident(SessionIncident incident, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void timer(Object event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void timer(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionStructureFactory getFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionConfig getConfig() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private class FailureTask implements Runnable {

        private final Exception e;

        FailureTask(Exception e) {
            this.e = e;
        }

        @Override
        public void run() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    class DelegatedTask implements Runnable {

        private final long id;

        private final Runnable delegate;

        private final boolean trace;

        DelegatedTask(Runnable delegate, boolean trace) {
            this.delegate = delegate;
            this.trace = trace;
            id = nextDelegatedTaskId.incrementAndGet();
        }

        @Override
        public void run() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class DelayedException extends Exception implements ICloseControllingException {

        private static final long serialVersionUID = 1L;

        private boolean fired;

        DelayedException(Throwable t) {
            super(t);
        }

        @Override
        public CloseType getCloseType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Throwable getClosingCause() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void markFired() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        boolean isFired() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
