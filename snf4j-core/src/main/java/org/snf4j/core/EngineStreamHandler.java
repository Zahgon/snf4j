/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020-2024 SNF4J contributors
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

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import org.snf4j.core.engine.HandshakeStatus;
import org.snf4j.core.engine.IEngine;
import org.snf4j.core.engine.IEngineResult;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.ITwoThresholdFuture;
import org.snf4j.core.handler.IStreamHandler;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.session.ISession;
import org.snf4j.core.session.IStreamSession;

class EngineStreamHandler extends AbstractEngineHandler<EngineStreamSession, IStreamHandler> implements IStreamHandler {

    private final static ByteBuffer[] EMPTY_ARRAY = new ByteBuffer[0];

    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.wrap(new byte[0]);

    private final ConcurrentLinkedQueue<ITwoThresholdFuture<Void>> pendingFutures = new ConcurrentLinkedQueue<ITwoThresholdFuture<Void>>();

    private final ByteBuffer[] DEFAULT_ARRAY = new ByteBuffer[1];

    private ITwoThresholdFuture<Void> polledFuture;

    private ByteBuffer[] outAppBuffers;

    private ByteBuffer inAppBuffer;

    private ByteBuffer outNetBuffer;

    private ByteBuffer inNetBuffer;

    private int maxAppBufferSize;

    private int maxNetBufferSize;

    private boolean sessionClosed;

    public EngineStreamHandler(IEngine engine, IStreamHandler handler, ILogger logger) {
        super(engine, handler, logger);
    }

    IStreamHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ByteBuffer getInNetBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final boolean handleClosing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void tryReleaseInAppBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void sessionClose(boolean isEof) {
        if (!sessionClosed) {
            sessionClosed = true;
            delayedCloseNeeded = false;
            session.close(isEof);
        }
    }

    @Override
    boolean unwrap(HandshakeStatus[] status) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void tryReleaseOutNetBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    boolean wrap(HandshakeStatus[] status) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final Executor getExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final boolean needUnwrap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    final void superQuickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void flush() {
        int position = outNetBuffer.position();
        if (position != 0) {
            boolean skipUpdate = false;
            outNetBuffer.flip();
            long futureThreshold = session.write0(outNetBuffer);
            if (session.optimizeBuffers) {
                outNetBuffer = null;
            } else {
                outNetBuffer.compact();
            }
            //update futures
            if (polledFuture != null) {
                if (polledFuture.getFirstThreshold() <= netCounter) {
                    polledFuture.setSecondThreshold(futureThreshold);
                } else {
                    skipUpdate = true;
                }
            }
            if (!skipUpdate) {
                while ((polledFuture = pendingFutures.poll()) != null && polledFuture.getFirstThreshold() <= netCounter) {
                    polledFuture.setSecondThreshold(futureThreshold);
                }
            }
        } else if (session.optimizeBuffers) {
            allocator.release(outNetBuffer);
            outNetBuffer = null;
        }
    }

    IFuture<Void> write(byte[] data, int offset, int length, boolean needFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> write(Object data, int length, boolean needFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void preCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void postEnding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final boolean ensure(int size) {
        if (size > inNetBuffer.remaining()) {
            try {
                minNetBufferSize = engine.getMinNetworkBufferSize();
                maxNetBufferSize = engine.getMaxNetworkBufferSize();
                inNetBuffer = allocator.ensure(inNetBuffer, size, minNetBufferSize, maxNetBufferSize);
            } catch (Exception e) {
                elogger.error(logger, "Reading failed for {}: {}", session, e);
                fireException(e);
                return false;
            }
        }
        return true;
    }

    @Override
    public void read(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setSession(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IStreamSession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(ByteBuffer buffer, boolean flipped) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(byte[] buffer, int off, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
