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
package org.snf4j.core.future;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.snf4j.core.session.ISession;

abstract class AbstractBlockingFuture<V> extends AbstractFuture<V> {

    volatile Throwable cause;

    private final FutureLock lock = new FutureLock();

    private volatile IFutureExecutor executor;

    AbstractBlockingFuture(ISession session) {
        super(session);
    }

    protected FutureLock getLock() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> await() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> await(long timeoutMillis) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> await(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> awaitUninterruptibly() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> awaitUninterruptibly(long timeoutMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> awaitUninterruptibly(long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @throws CancellationException
     *             if the future was cancelled
     */
    IFuture<V> rethrow() throws ExecutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> sync() throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> sync(long timeoutMillis) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> sync(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> syncUninterruptibly() throws ExecutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> syncUninterruptibly(long timeoutMillis) throws ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<V> syncUninterruptibly(long timeout, TimeUnit unit) throws ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void setExecutor(IFutureExecutor executor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final V get0() throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean cancel(boolean arg0) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Throwable cause() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void notifyWaiters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void checkDeadLock() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IFuture<V> awaitUninterruptibly0(long nanos) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IFuture<V> await0(long nanos, boolean interruptable) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
