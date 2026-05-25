/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2017-2022 SNF4J contributors
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
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.snf4j.core.SessionPipeline.Item;
import org.snf4j.core.factory.DefaultSelectorLoopStructureFactory;
import org.snf4j.core.factory.DefaultThreadFactory;
import org.snf4j.core.factory.ISelectorLoopStructureFactory;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.IFutureExecutor;
import org.snf4j.core.future.RegisterFuture;
import org.snf4j.core.future.TaskFuture;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.logger.ExceptionLogger;
import org.snf4j.core.logger.IExceptionLogger;
import org.snf4j.core.logger.ILogger;

abstract class InternalSelectorLoop extends IdentifiableObject implements IFutureExecutor {

    final ILogger logger;

    final IExceptionLogger elogger = ExceptionLogger.getInstance();

    private final static AtomicLong nextId = new AtomicLong(0);

    volatile Thread thread;

    ThreadFactory threadFactory = DefaultThreadFactory.DEFAULT;

    volatile Executor executor = DefaultExecutor.DEFAULT;

    final ISelectorLoopStructureFactory factory;

    volatile Selector selector;

    private final long selectTimeout;

    private long selectBeginTime;

    private long selectEndTime;

    private volatile long totalWorkTime;

    private volatile long totalWaitTime;

    private AtomicBoolean wakenup = new AtomicBoolean(false);

    private int selectCounter;

    private volatile int size;

    private int prevSize;

    private final static int SELECTOR_REBUILD_THRESHOLD = Integer.getInteger(Constants.SELECTOR_REBUILD_THRESHOLD_SYSTEM_PROPERY, 512);

    private AtomicBoolean rebuildRequested = new AtomicBoolean(false);

    private AtomicBoolean stoppingRequested = new AtomicBoolean(false);

    final AtomicReference<StoppingType> stopping = new AtomicReference<StoppingType>();

    private Set<SelectionKey> stoppingKeys;

    private boolean closeWhenEmpty;

    private boolean ending;

    private boolean inTask;

    private final ConcurrentLinkedQueue<PendingRegistration> registrations = new ConcurrentLinkedQueue<PendingRegistration>();

    private final ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<Task>();

    private final Object registrationLock = new Object();

    private Set<SelectionKey> invalidatedKeys = new HashSet<SelectionKey>();

    boolean areSwitchings;

    final List<InternalSession> switchings = new LinkedList<InternalSession>();

    boolean debugEnabled;

    boolean traceEnabled;

    /**
     * Constructs a internal selector loop
     *
     * @param name
     *            the name for this selector loop, or <code>null</code> if the name should
     *            be auto generated
     * @param logger
     *            the logger that should be used by this selector loop to log messages
     * @throws IOException
     *             if the {@link java.nio.channels.Selector Selector} associated with this
     *             selector loop could not be opened
     */
    InternalSelectorLoop(String name, ILogger logger, ISelectorLoopStructureFactory factory) throws IOException {
        super("selector-loop-", nextId.incrementAndGet(), name);
        this.logger = logger;
        this.factory = factory == null ? DefaultSelectorLoopStructureFactory.DEFAULT : factory;
        selector = this.factory.openSelector();
        selectTimeout = Math.max(0, Long.getLong(Constants.SELECTOR_SELECT_TIMEOUT, 1000));
    }

    /**
     * Returns the total time in nanoseconds this selector loop spent waiting
     * for I/O operations
     *
     * @return the total time in nanoseconds
     */
    public long getTotalWaitTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the total time in nanoseconds this selector loop spent processing
     * I/O operations
     *
     * @return the total time in nanoseconds
     */
    public long getTotalWorkTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Rebuilds the associated selector by replacing it with newly created one. All valid
     * selection keys registered with the current selector will be re-registered to the
     * new selector.
     * <p>
     * This operation is asynchronous.
     */
    public void rebuild() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void rebuildSelector() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void notifySizeChange(final boolean notify) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void select() throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void elogWarnOrError(ILogger log, String msg, Object... args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the thread factory used to create a thread for this selector loop.
     * To take effect it have to be set before execution of the
     * <code>start</code> method.
     *
     * @param threadFactory
     *            the new thread factory
     * @throws NullPointerException
     *             If the <code>threadFactory</code> argument is
     *             <code>null</code>
     */
    public void setThreadFactory(ThreadFactory threadFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the thread factory used to create a thread for this selector loop.
     *
     * @return the current thread factory
     */
    public ThreadFactory getThreadFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the executor that will be used to execute delegated tasks required
     * by engine driven sessions to complete operations that block, or may take
     * an extended period of time to complete.
     *
     * @param executor
     *            the new executor
     * @throws NullPointerException
     *             If the <code>executor</code> argument is <code>null</code>
     */
    public void setExecutor(Executor executor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the executor that is used to execute delegated tasks required by
     * engine driven sessions to complete operations that block, or may take an
     * extended period of time to complete.
     *
     * @return the current executor
     */
    public Executor getExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the size of the selector's key set associated with the selector loop
     *
     * @return the size of the selector's key set
     * @throws ClosedSelectorException
     *             if the associated selector is closed
     */
    public int getSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if the selector associated with this selector loop is open.
     *
     * @return <code>true</code> if the associated selector is open
     */
    public boolean isOpen() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Starts the selector loop in a new thread or in the current one.
     *
     * @param inCurrentThread
     *            <code>true</code> if the selector loop should run in the
     *            current thread
     * @throws ClosedSelectorException
     *             if the internal selector is closed
     */
    public void start(boolean inCurrentThread) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Starts the selector loop in a new thread.
     *
     * @throws ClosedSelectorException
     *             if the internal selector is closed
     */
    public void start() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wakes up the associated selector.
     *
     * @see java.nio.channels.Selector#wakeup() Selector.wakeup()
     */
    public void wakeup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wakes up the associated selector only if executed from outside this
     * loop's thread.
     */
    final void lazyWakeup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if the current {@link Thread} is executed in this selector loop.
     *
     * @return <code>true</code> the current {@link Thread} is executed in this selector loop.
     */
    final boolean inLoop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final boolean inTask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public final boolean inExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final Selector getUnderlyingSelector(Selector selector) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void loop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void handleTasks() {
        Task task;
        inTask = true;
        while ((task = tasks.poll()) != null) {
            handleTask(task);
        }
        inTask = false;
    }

    private final void handleTask(Task task) {
        TaskFuture<Void> future = task.future;
        try {
            if (traceEnabled) {
                logger.trace("Starting execution of task {}", task.task);
            }
            task.task.run();
            if (traceEnabled) {
                logger.trace("Finished execution of task {}", task.task);
            }
            if (future != null) {
                future.success();
            }
            if (areSwitchings) {
                handleSwitchings();
            }
        } catch (Throwable e) {
            elogger.error(logger, "Unexpected exception thrown during execution of task {}: {}", task.task, e);
            if (future != null) {
                future.abort(e);
            }
        }
    }

    private final void handleRegistration(SelectionKey key, PendingRegistration reg) throws Exception {
        if (reg.ctx.isSession()) {
            handleRegisteredKey(key, reg);
        } else {
            reg.ctx.postRegistration(reg.channel);
        }
        reg.future.success();
    }

    private final void abortRegistration(PendingRegistration reg, boolean closeChannel, Throwable cause) {
        final ChannelContext<?> ctx = reg.ctx;
        if (closeChannel) {
            try {
                ctx.close(reg.channel);
            } catch (Throwable e) {
                elogger.warn(logger, "Closing of channel {} during aborting registration failed: {}", ctx.toString(reg.channel), e);
            }
        }
        if (ctx.isSession()) {
            InternalSession session = ctx.getSession();
            if (session.isCreated()) {
                fireEndingEvent(session, false);
            } else {
                session.abortFutures(cause);
            }
        } else {
            ctx.postClose(reg.channel);
        }
        reg.future.abort(cause);
    }

    void stop(StoppingType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gently stops this selector loop. As a result, all associated sessions
     * will be gently closed by calling the {@link org.snf4j.core.session.ISession#close() close} method.
     * <p>
     * This method is asynchronous.
     */
    public void stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Quickly stops this selector loop. As a result, all associated sessions
     * will be quickly closed by calling the {@link org.snf4j.core.session.ISession#quickClose() quickClose} method.
     * <p>
     * This method is asynchronous.
     */
    public void quickStop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Quickly stops this selector loop. As a result, all associated sessions
     * will be quickly closed by calling the {@link org.snf4j.core.session.ISession#quickClose() dirtyClose} method.
     * <p>
     * This method is asynchronous.
     */
    public void dirtyStop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if this selector is in the process of stopping.
     *
     * @return <code>true</code> if stopping is in progress or this selector loop is already stopped
     */
    public boolean isStopping() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if this selector loop is stopped. In other words, it means that
     * the associated thread has stopped running.
     *
     * @return <code>true</code> if this selector loop is stopped
     */
    public boolean isStopped() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Waits at most <code>millis</code> milliseconds for this selector loop's
     * thread to die.
     *
     * @param millis
     *            the time to wait in milliseconds
     * @throws InterruptedException
     *             if any thread has interrupted the current thread
     * @throws IllegalArgumentException
     *             if the value of <code>millis</code> is negative
     * @return <code>true</code> if the selector loop's thread died
     */
    public final boolean join(long millis) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Waits for this selector loop's thread to die.
     *
     * @throws InterruptedException
     *             if any thread has interrupted the current thread
     */
    public final void join() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds a channel to the pending registration queue.
     * @throws ClosedChannelException
     *             if the channel is closed
     * @throws SelectorLoopStoppingException
     *             if selector loop is in the process of stopping
     * @throws ClosedSelectorException
     *             if the internal selector is closed
     * @throws IllegalArgumentException
     *             if a bit in ops does not correspond to an operation that is
     *             supported by this channel
     */
    IFuture<Void> register(SelectableChannel channel, int ops, ChannelContext<?> ctx) throws ClosedChannelException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Executes a task in the selector-loop's thread. This operation is
     * asynchronous.
     * <p>
     * This method should be used whenever there will be no need to synchronize
     * on a future associated with the specified task. This will save some
     * resources and may improve performance.
     *
     * @param task
     *            task to be executed in the selector-loop's thread
     * @throws SelectorLoopStoppingException
     *             if selector loop is in the process of stopping
     * @throws IllegalArgumentException
     *             if {@code task} is null
     */
    public void executenf(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Executes a task in the selector-loop's thread. This operation is
     * asynchronous.
     *
     * @param task
     *            task to be executed in the selector-loop's thread
     * @throws SelectorLoopStoppingException
     *             if selector loop is in the process of stopping
     * @throws IllegalArgumentException
     *             if {@code task} is null
     * @return the future associated with the specified task
     */
    public IFuture<Void> execute(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void execute0(Task task) {
        synchronized (registrationLock) {
            //make sure not to register while stopping
            if (ending) {
                throw new SelectorLoopStoppingException();
            }
            tasks.add(task);
        }
        wakeup();
    }

    final void execute0(Runnable task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Registers the invalidated key for finishing. The method does nothing if
     * run in the selector's thread that is not currently executing any task.
     *
     * @param key
     *            the key that was invalidated
     */
    final void finishInvalidatedKey(SelectionKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the new invalidated keys.
     *
     * @return the new invalidated keys or null if there were no new invalidated
     *         keys.
     */
    final SelectionKey[] getInvalidatedKeysToFinish() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final boolean fireCreatedEvent(final InternalSession session, SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireEndingEvent(final InternalSession session, boolean skipCloseWhenEmpty) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireEvent(final InternalSession session, SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireEvent(final InternalSession session, DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireException(final SelectionKey key, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireException(final InternalSession session, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void handleInvalidKey(SelectionKey key, Set<SelectionKey> stoppingKeys, boolean skipCloseWhenEmpty) throws IOException {
        if (stoppingKeys != null) {
            stoppingKeys.remove(key);
        }
        ChannelContext<?> ctx = (ChannelContext<?>) key.attachment();
        if (ctx.isSession()) {
            InternalSession session = ctx.getSession();
            try {
                session.close(key.channel());
            } finally {
                fireEvent(session, SessionEvent.CLOSED);
                fireEndingEvent(session, skipCloseWhenEmpty);
            }
        }
    }

    void switchSession(InternalSession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleSwitchings() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void handleInvalidKey(SelectionKey key, Set<SelectionKey> stoppingKeys) throws IOException {
        handleInvalidKey(key, stoppingKeys, false);
    }

    abstract void handleRegisteredKey(SelectionKey key, PendingRegistration reg) throws Exception;

    abstract SelectionKey handleSelectedKey(SelectionKey key);

    abstract void notifyAboutLoopSizeChange(int newSize, int prevSize);

    abstract boolean notifyAboutLoopChanges();

    static final class PendingRegistration {

        SelectableChannel channel;

        int ops;

        ChannelContext<?> ctx;

        RegisterFuture<Void> future;
    }

    static final class Task {

        Runnable task;

        TaskFuture<Void> future;
    }

    class Loop implements Runnable {

        @Override
        public void run() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
