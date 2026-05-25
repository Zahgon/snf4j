/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2024 SNF4J contributors
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
package org.snf4j.example.earlydata;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.snf4j.core.codec.ICodecExecutor;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.ISession;
import org.snf4j.tls.engine.IEarlyDataHandler;

public class EarlyDataClientHandler extends EarlyDataHandler implements IEarlyDataHandler {

    private final String cmd;

    private Queue<byte[]> earlyData;

    private boolean earlyDataAccepted;

    public EarlyDataClientHandler(String cmd) {
        this.cmd = cmd;
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasEarlyData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public byte[] nextEarlyData(String protocol) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void acceptedEarlyData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void rejectedEarlyData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
