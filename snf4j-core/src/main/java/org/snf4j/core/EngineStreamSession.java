/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2019-2024 SNF4J contributors
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

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.concurrent.Executor;
import org.snf4j.core.engine.IEngine;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.handler.IStreamHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.session.IEngineStreamSession;

/**
 * A stream-oriented session that handles protocols driven by customized protocol engines
 * implementing the {@link IEngine} interface.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class EngineStreamSession extends StreamSession implements IEngineStreamSession {

    private final EngineStreamHandler internal;

    private volatile Executor executor;

    /**
     * Constructs a named stream-oriented session associated with a protocol
     * engine and a handler.
     *
     * @param name
     *            the name for this session, or <code>null</code> if the
     *            handler's name should be used for this session's name
     * @param engine
     *            the protocol engine driving this session
     * @param handler
     *            the handler that should be associated with this session
     * @param logger
     *            the logger used to log messages related with this session
     */
    public EngineStreamSession(String name, IEngine engine, IStreamHandler handler, ILogger logger) {
        super(name, new EngineStreamHandler(engine, handler, logger));
        internal = (EngineStreamHandler) this.handler;
        executor = handler.getFactory().getExecutor();
        engine.link(this);
    }

    /**
     * Constructs a stream-oriented session associated with a protocol
     * engine and a handler.
     *
     * @param engine
     *            the protocol engine driving this session
     * @param handler
     *            the handler that should be associated with this session
     * @param logger
     *            the logger used to log messages related with this session
     */
    public EngineStreamSession(IEngine engine, IStreamHandler handler, ILogger logger) {
        super(new EngineStreamHandler(engine, handler, logger));
        internal = (EngineStreamHandler) this.handler;
        executor = handler.getFactory().getExecutor();
        engine.link(this);
    }

    @Override
    IEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    IStreamReader superCodec() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    ByteBuffer[] getInBuffersForCopying() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setExecutor(Executor executor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Executor getExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void beginHandshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void beginLazyHandshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object getEngineSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IStreamHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void controlCloseException(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final IFuture<Void> write0(byte[] data, int offset, int length, boolean needFuture) {
        checkKey(key);
        if (closing == ClosingState.NONE) {
            return internal.write(data, offset, length, needFuture);
        }
        return needFuture ? futuresController.getCancelledFuture() : null;
    }

    @Override
    public IFuture<Void> write(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final IFuture<Void> write0(ByteBuffer data, int length, boolean needFuture) {
        checkKey(key);
        if (closing == ClosingState.NONE) {
            return internal.write(data, length, needFuture);
        }
        return needFuture ? futuresController.getCancelledFuture() : null;
    }

    @Override
    public IFuture<Void> write(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final IFuture<Void> write0(IByteBufferHolder holder, boolean needFuture) {
        checkKey(key);
        if (closing == ClosingState.NONE) {
            return internal.write(holder, -1, needFuture);
        }
        return needFuture ? futuresController.getCancelledFuture() : null;
    }

    @Override
    public IFuture<Void> write(ByteBuffer data, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer data, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(IByteBufferHolder holder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(IByteBufferHolder holder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void quickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void superQuickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void superClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void dirtyClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void preCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void postEnding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private class EncodeTaskWriter implements IEncodeTaskWriter {

        @Override
        public IFuture<Void> write(SocketAddress remoteAddress, ByteBuffer buffer, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IFuture<Void> write(SocketAddress remoteAddress, byte[] bytes, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IFuture<Void> write(SocketAddress remoteAddress, IByteBufferHolder holder, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
