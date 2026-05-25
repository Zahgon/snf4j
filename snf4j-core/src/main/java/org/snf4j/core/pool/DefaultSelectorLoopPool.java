/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2017-2019 SNF4J contributors
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
package org.snf4j.core.pool;

import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectableChannel;
import java.util.Arrays;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.snf4j.core.IdentifiableObject;
import org.snf4j.core.SelectorLoop;
import org.snf4j.core.factory.DefaultSelectorLoopStructureFactory;
import org.snf4j.core.factory.DefaultThreadFactory;
import org.snf4j.core.factory.ISelectorLoopStructureFactory;
import org.snf4j.core.logger.ExceptionLogger;
import org.snf4j.core.logger.IExceptionLogger;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.logger.LoggerFactory;

/**
 * Default implementation for selector loop pool that is backed by an fixed-size array of selector loops.
 * <p>
 * This implementation is not thread safe.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class DefaultSelectorLoopPool extends IdentifiableObject implements ISelectorLoopPool {

    private final static ILogger logger = LoggerFactory.getLogger(DefaultSelectorLoopPool.class);

    private final IExceptionLogger elogger = ExceptionLogger.getInstance();

    private static AtomicLong nextId = new AtomicLong(0);

    /**
     * The backing array for this selector-pool
     */
    protected final SelectorLoop[] pool;

    /**
     * A factory used to create threads for all selector loops in this pool
     */
    protected final ThreadFactory threadFactory;

    /**
     * A factory used to create selectors for all selector loops in this pool
     */
    protected final ISelectorLoopStructureFactory selectorFactory;

    /**
     * The current size of this pool
     */
    protected int size;

    /**
     * Constructs a named selector loop pool with given capacity and thread
     * factory.
     *
     * @param name
     *            the name of the pool or <code>null</code> if the name should
     *            be auto generated
     * @param capacity
     *            the capacity of this pool
     * @param threadFactory
     *            a factory used to create threads for all selector loops in
     *            this pool, or <code>null</code> to use the default factory
     * @param selectorFactory
     *            a selector factory used to create selectors for all selector
     *            loops in this pool, or <code>null</code> to use the default
     *            factory
     *
     *            that will be used by this selector loop to open its selector,
     *            or <code>null</code> if default factory should be used
     */
    public DefaultSelectorLoopPool(String name, int capacity, ThreadFactory threadFactory, ISelectorLoopStructureFactory selectorFactory) {
        super("selector-pool-", nextId.incrementAndGet(), name);
        this.threadFactory = threadFactory != null ? threadFactory : DefaultThreadFactory.DEFAULT;
        this.selectorFactory = selectorFactory != null ? selectorFactory : DefaultSelectorLoopStructureFactory.DEFAULT;
        pool = new SelectorLoop[capacity];
    }

    /**
     * Constructs a named selector loop pool with given capacity.
     *
     * @param name
     *            the name of the pool or <code>null</code> if the name should
     *            be auto generated
     * @param capacity
     *            the capacity of this pool
     */
    public DefaultSelectorLoopPool(String name, int capacity) {
        this(name, capacity, null, null);
    }

    /**
     * Constructs a unnamed selector loop pool with given capacity.
     *
     * @param capacity
     *            the capacity of this pool
     */
    public DefaultSelectorLoopPool(int capacity) {
        this(null, capacity, null, null);
    }

    /**
     * Gets an array of all selector loops created in this pool.
     *
     * @return the array of selector loops.
     */
    public SelectorLoop[] getPool() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the capacity of this pool.
     *
     * @return the capacity of this pool
     */
    public int getCapacity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the current size of this pool. It is the number of selector loops
     * created in this pool.
     *
     * @return the current size
     */
    public int getSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    SelectorLoop createLoop(String name) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * @return the selector loop with the smallest number of the registered channels
     * @see org.snf4j.core.SelectorLoop AbstractSelectorLoop#getSize
     */
    @Override
    public SelectorLoop getLoop(SelectableChannel channel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void quickStop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void update(SelectorLoop loop, int newSize, int prevSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean join(long millis) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void join() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
