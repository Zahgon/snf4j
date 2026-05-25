/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2017-2024 SNF4J contributors
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
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.snf4j.core.SessionPipeline.Item;
import org.snf4j.core.allocator.IByteBufferAllocator;
import org.snf4j.core.codec.ICodecExecutor;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.SessionFuturesController;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.IAllocatingHandler;
import org.snf4j.core.handler.IHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.logger.ExceptionLogger;
import org.snf4j.core.logger.IExceptionLogger;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.session.AbstractSession;
import org.snf4j.core.session.ISession;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.session.ISessionTimer;
import org.snf4j.core.session.IllegalSessionStateException;
import org.snf4j.core.session.SessionState;
import org.snf4j.core.session.UnsupportedSessionTimer;
import org.snf4j.core.timer.ITimer;

abstract class InternalSession extends AbstractSession implements ISession {

    final ILogger logger;

    final IExceptionLogger elogger = ExceptionLogger.getInstance();

    private final static AtomicLong nextId = new AtomicLong(0);

    volatile ClosingState closing = ClosingState.NONE;

    final AtomicBoolean closeCalled = new AtomicBoolean(false);

    private volatile long readBytes;

    private volatile long writtenBytes;

    private long lastThroughputCalculationTime;

    private long lastReadBytes;

    private long lastWrittenBytes;

    private volatile double readBytesThroughput;

    private volatile double writtenBytesThroughput;

    private final long creationTime;

    private volatile long lastReadTime;

    private volatile long lastWriteTime;

    private volatile long lastIoTime;

    private volatile boolean readSuspended;

    private volatile boolean writeSuspended;

    final IHandler handler;

    final ISessionConfig config;

    final IByteBufferAllocator allocator;

    volatile SelectionKey key;

    volatile SelectableChannel channel;

    volatile InternalSelectorLoop loop;

    volatile boolean isEOS;

    /**
     * Used to synchronize write operations and changing key's selection interests
     */
    final Object writeLock = new Object();

    /**
     * Used to track already fired events.
     */
    int eventBits;

    final SessionFuturesController futuresController = new SessionFuturesController(this);

    final CodecExecutorAdapter codec;

    final boolean optimizeCopying;

    final boolean optimizeBuffers;

    private final ISessionTimer timer;

    final int maxWriteSpinCount;

    volatile SessionPipeline<?> pipeline;

    Item<?> pipelineItem;

    boolean isSwitching;

    protected InternalSession(String name, IHandler handler, CodecExecutorAdapter codec, ILogger logger) {
        super("Session-", nextId.incrementAndGet(), name != null ? name : (handler != null ? handler.getName() : null), handler != null ? handler.getFactory().getAttributes() : null);
        if (handler == null)
            throw new IllegalArgumentException("handler is null");
        this.logger = logger;
        this.handler = handler;
        this.handler.setSession(this);
        if (handler instanceof IAllocatingHandler) {
            allocator = ((IAllocatingHandler) handler).getAllocator();
        } else {
            allocator = handler.getFactory().getAllocator();
        }
        config = handler.getConfig();
        optimizeCopying = config.optimizeDataCopying();
        optimizeBuffers = optimizeCopying && allocator.isReleasable();
        maxWriteSpinCount = config.getMaxWriteSpinCount();
        if (maxWriteSpinCount <= 0) {
            throw new IllegalArgumentException("maxWriteSpinCount is " + maxWriteSpinCount + " (expected 1+)");
        }
        creationTime = System.currentTimeMillis();
        lastReadTime = lastWriteTime = lastIoTime = lastThroughputCalculationTime = creationTime;
        if (codec == null) {
            ICodecExecutor executor = config.createCodecExecutor();
            this.codec = executor != null ? new CodecExecutorAdapter(executor, this) : null;
        } else {
            this.codec = codec;
        }
        ITimer timer = handler.getFactory().getTimer();
        if (timer == null) {
            this.timer = UnsupportedSessionTimer.INSTANCE;
        } else {
            this.timer = new InternalSessionTimer(InternalSession.this, timer);
        }
    }

    protected InternalSession(String name, IHandler handler, ILogger logger) {
        this(name, handler, null, logger);
    }

    abstract IEncodeTaskWriter getEncodeTaskWriter();

    abstract SessionPipeline<?> createPipeline();

    SessionPipeline<?> getPipeline0() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setPipeline(SessionPipeline<?> pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    InternalSession getFirstInPipeline() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void abortFutures(Throwable cause) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> getCreateFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> getOpenFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> getReadyFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> getCloseFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> getEndFuture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Detects if the key was replaced. It can happen after rebuilding of the selector.
     *
     * @throws IllegalSessionStateException
     *             if replacement occurred and new key is invalid
     */
    final SelectionKey detectRebuild(SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Throws unchecked exception if the key is not valid
     *
     * @throws IllegalSessionStateException
     *             if key is not valid
     */
    static SelectionKey checkKey(SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void lazyWakeup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionConfig getConfig() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ICodecPipeline getCodecPipeline() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SessionState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isOpen() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setChannel(SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setSelectionKey(SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setLoop(InternalSelectorLoop loop) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final Object getWriteLock() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void incReadBytes(long bytes, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void incWrittenBytes(long bytes, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Clears key's write interest if write is not suspended. It should be
     * executed inside block synchronized on a write lock.
     */
    void clearWriteInterestOps(SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets key's write interest if write is not suspended. It should be
     * executed inside block synchronized on a write lock.
     *
     * @throw CancelledKeyException if the key has been canceled
     */
    void setWriteInterestOps(SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Suspends read, write or both if session is not in closing state. It
     * should be executed inside block synchronized on a write lock.
     *
     * @param ops
     *            SelectionKey.OP_RAED, SelectionKey.OP_WRITE or both
     * @throws CancelledKeyException
     *             if the selection key associated with this session has been
     *             cancelled
     */
    boolean suspend(int ops) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void close(boolean isEos) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void close(boolean isEos, boolean sending) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void quickClose0() {
        SelectionKey key = this.key;
        closeCalled.set(true);
        if (key != null && key.isValid()) {
            try {
                synchronized (writeLock) {
                    key = detectRebuild(key);
                    closing = ClosingState.FINISHED;
                    close(key.channel());
                }
            } catch (Exception e) {
            }
        } else if (channel != null) {
            try {
                close(channel);
            } catch (IOException e) {
            }
        }
        if (key != null) {
            loop.finishInvalidatedKey(key);
        }
    }

    @Override
    public void quickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void dirtyClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Handles closing operation being in progress. It should be executed only
     * when the output buffers have no more data after compacting. It should be executed inside
     * the same synchronized block as the compacting method
     * @see compactOutBuffers
     */
    void handleClosingInProgress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Resumes read, write or both if session is not in closing state. It should
     * be executed inside block synchronized on a write lock.
     *
     * @param ops
     *            SelectionKey.OP_RAED, SelectionKey.OP_WRITE or both
     * @throws CancelledKeyException
     *             if the selection key associated with this session has been
     *             cancelled
     */
    boolean resume(int ops) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void suspendRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void suspendWrite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void resumeRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void resumeWrite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isReadSuspended() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isWriteSuspended() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final long getReadBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final long getWrittenBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void calculateThroughput(long currentTime, boolean force) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final double getReadBytesThroughput() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final double getWrittenBytesThroughput() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final long getCreationTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final long getLastIoTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final long getLastReadTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final long getLastWriteTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionTimer getTimer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isDataCopyingOptimized() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ByteBuffer allocate(int capacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void release(ByteBuffer buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> execute(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void executenf(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final boolean wasException() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final boolean isValid(EventType eventType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void event(DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void timer(Object event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void timer(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void controlCloseException(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Throwable controlClose(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void fireException(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true the exception was triggered
     */
    boolean fireException(SessionIncident incident, Object event, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean incident(SessionIncident incident, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract void preCreated();

    abstract void postEnding();

    int copyInBuffer(InternalSession oldSession) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void consumeInBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void close(SelectableChannel channel) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void shutdown(SelectionKey key) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void closeAndFinish(SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void checkBounds(int offset, int length, int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
