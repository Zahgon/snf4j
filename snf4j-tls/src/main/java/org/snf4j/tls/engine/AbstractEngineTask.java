/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2023-2024 SNF4J contributors
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

import java.util.concurrent.atomic.AtomicBoolean;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.HandshakeFailureAlert;
import org.snf4j.tls.alert.InternalErrorAlert;

abstract public class AbstractEngineTask implements IEngineTask {

    private final AtomicBoolean started = new AtomicBoolean();

    private volatile Throwable cause;

    private volatile boolean done;

    @Override
    public void run() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void run(EngineState state) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isSuccessful() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Throwable cause() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract void execute() throws Exception;
}
