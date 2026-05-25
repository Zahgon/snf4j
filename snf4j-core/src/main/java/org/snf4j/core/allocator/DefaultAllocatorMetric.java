/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020 SNF4J contributors
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
package org.snf4j.core.allocator;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Metric for the {@link DefaultAllocator}.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class DefaultAllocatorMetric implements IDefaultAllocatorMetricCollector {

    private final AtomicLong allocatingCount = new AtomicLong();

    private final AtomicLong allocatedCount = new AtomicLong();

    private final AtomicLong releasingCount = new AtomicLong();

    private final AtomicLong releasedCount = new AtomicLong();

    private final AtomicLong ensureSomeCount = new AtomicLong();

    private final AtomicLong ensureCount = new AtomicLong();

    private final AtomicLong reduceCount = new AtomicLong();

    private final AtomicLong extendCount = new AtomicLong();

    private final AtomicInteger maxCapacity = new AtomicInteger();

    private final void setMaxCapacity(final int size) {
        int current;
        do {
            current = maxCapacity.get();
        } while (size > current && !maxCapacity.compareAndSet(current, size));
    }

    @Override
    public void allocating(int capacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void allocated(int capacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void released(int capacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void releasing(int capacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ensureSome() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ensure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reduce() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void extend() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of allocations that have been performed by
     * the associated allocator.
     *
     * @return the total number of allocations
     */
    public long getAllocatingCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of true allocations that have been performed by the
     * associated allocator.
     *
     * @return the total number of real allocations
     */
    public long getAllocatedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of buffers that have been requested for releasing by the
     * associated allocator.
     *
     * @return the total number of released buffers
     */
    public long getReleasingCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of buffers that have been released by the associated
     * allocator.
     *
     * @return the total number of released buffers
     */
    public long getReleasedCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of re-allocations that have been performed by
     * the {@link IByteBufferAllocator#ensureSome ensureSome} method in
     * the associated allocator.
     *
     * @return the total number of re-allocations
     */
    public long getEnsureSomeCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of re-allocations that have been performed by
     * the {@link IByteBufferAllocator#ensure ensure} method in
     * the associated allocator.
     *
     * @return the total number of re-allocations
     */
    public long getEnsureCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of re-allocations that have been performed by
     * the {@link IByteBufferAllocator#reduce reduce} method in
     * the associated allocator.
     *
     * @return the total number of re-allocations
     */
    public long getReduceCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the total number of re-allocations that have been performed by
     * the {@link IByteBufferAllocator#extend extend} method in
     * the associated allocator.
     *
     * @return the total number of re-allocations
     */
    public long getExtendCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the capacity of the biggest buffer allocated by the associated allocator.
     *
     * @return the max capacity
     */
    public int getMaxCapacity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
