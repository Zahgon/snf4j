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

import java.util.concurrent.atomic.AtomicReference;
import org.snf4j.core.session.ISession;

/**
 * Base implementation of the {@link IFuture} interface.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public abstract class AbstractFuture<V> implements IFuture<V> {

    AtomicReference<FutureState> state = new AtomicReference<FutureState>();

    private final ISession session;

    /**
     * Constructs a base implementation with the specified session.
     *
     * @param session
     *            the session this future is associated with
     */
    protected AbstractFuture(ISession session) {
        this.session = session;
    }

    boolean setState(FutureState state) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String toStringDetails() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a string representation of this future.
     *
     * @return a string representation of this future.
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if if the operation associated with this future completed.
     *
     * @return <code>true</code> if the operation completed
     */
    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if the operation associated with this future was cancelled
     * before it completed normally.
     *
     * @return <code>true</code> if the operation was cancelled
     */
    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isSuccessful() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isFailed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns <code>null</code>.
     *
     * @return <code>null</code>
     */
    @Override
    public V getNow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
