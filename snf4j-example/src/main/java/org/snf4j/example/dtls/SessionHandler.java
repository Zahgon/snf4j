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
package org.snf4j.example.dtls;

import java.net.SocketAddress;
import org.snf4j.core.EndingAction;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.AbstractDatagramHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.timer.ITimerTask;

public class SessionHandler extends AbstractDatagramHandler {

    final static Object RETRY_EVENT = new Object();

    final static int RETRY_COUNT = 5;

    final static int RETRY_TIMEOUT = 1000;

    final static Object IDLE_EVENT = new Object();

    final static int IDLE_TIMEOUT = 30000;

    final boolean clientMode;

    long sequence;

    long retries;

    ITimerTask retryTimer;

    ITimerTask idleTimer;

    int retryLeft;

    SessionHandler(boolean clientMode) {
        this.clientMode = clientMode;
    }

    @Override
    public void read(SocketAddress remoteAddress, Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setRetryTimer(boolean resetCounter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setIdleTimer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void cancelTimers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleClient(Packet packet) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleServer(Packet packet) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void timer(Object event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void exception(Throwable e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean incident(SessionIncident incident, Throwable t) {
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

    void log(Object s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void err(Object s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void stats() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
