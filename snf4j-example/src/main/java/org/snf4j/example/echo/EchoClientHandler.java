/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020-2021 SNF4J contributors
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
package org.snf4j.example.echo;

import java.nio.ByteBuffer;
import org.snf4j.core.EndingAction;
import org.snf4j.core.allocator.IByteBufferAllocator;
import org.snf4j.core.allocator.ThreadLocalCachingAllocator;
import org.snf4j.core.factory.DefaultSessionStructureFactory;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.AbstractStreamHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.session.DefaultSessionConfig;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.session.ssl.SSLEngineBuilder;

public class EchoClientHandler extends AbstractStreamHandler {

    private static final IByteBufferAllocator ALLOCATOR = new ThreadLocalCachingAllocator(true);

    private final DefaultSessionConfig config;

    private long startTime;

    private long totalBytes;

    EchoClientHandler() {
        this(null);
    }

    EchoClientHandler(SSLEngineBuilder builder) {
        config = new SessionConfig(EchoClient.PIPELINE_SIZE).setEndingAction(EndingAction.STOP).setOptimizeDataCopying(true).setMinOutBufferCapacity(EchoClient.SIZE << 1);
        if (builder != null) {
            config.addSSLEngineBuilder(builder);
        }
    }

    boolean read(int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
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

    private void stats() {
        long bytesPerSecond = 1000 * totalBytes / (System.currentTimeMillis() - startTime);
        Logger.inf("avg bytes/sec: " + bytesPerSecond);
    }

    @Override
    public ISessionConfig getConfig() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionStructureFactory getFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
