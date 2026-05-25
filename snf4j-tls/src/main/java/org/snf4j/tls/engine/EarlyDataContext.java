/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2023 SNF4J contributors
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
package org.snf4j.tls.engine;

import static org.snf4j.tls.engine.EarlyDataState.PROCESSED;
import static org.snf4j.tls.engine.EarlyDataState.PROCESSING;
import static org.snf4j.tls.engine.EarlyDataState.REJECTED;
import static org.snf4j.tls.engine.EarlyDataState.REJECTING;
import org.snf4j.tls.cipher.CipherSuite;

public class EarlyDataContext implements IEarlyDataContext {

    private final CipherSuite cipherSuite;

    private EarlyDataState state;

    private long sizeCountdown;

    public EarlyDataContext(CipherSuite cipherSuite, boolean rejecting, long maxSize) {
        state = rejecting ? REJECTING : PROCESSING;
        this.sizeCountdown = maxSize;
        this.cipherSuite = cipherSuite;
    }

    public EarlyDataContext(CipherSuite cipherSuite, long maxSize) {
        this(cipherSuite, false, maxSize);
    }

    @Override
    public EarlyDataState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void complete() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void rejecting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void incProcessedBytes(int amount) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isSizeLimitExceeded() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CipherSuite getCipherSuite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
