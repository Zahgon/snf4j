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

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import org.snf4j.core.engine.IEngine;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.handler.IDatagramHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.logger.LoggerFactory;
import org.snf4j.core.session.IEngineDatagramSession;

class EngineDatagramServerSession extends DatagramServerSession implements IEngineDatagramSession {

    private final static ILogger LOGGER = LoggerFactory.getLogger(EngineDatagramServerSession.class);

    private final EngineDatagramWrapper wrapper;

    EngineDatagramServerSession(IEngine engine, DatagramSession delegate, SocketAddress remoteAddress, IDatagramHandler handler) {
        super(delegate, remoteAddress, new EngineDatagramHandler(engine, remoteAddress, handler, LOGGER));
        wrapper = new EngineDatagramWrapper(remoteAddress, (EngineDatagramHandler) this.handler);
        wrapper.setExecutor(handler.getFactory().getExecutor());
        engine.link(this);
    }

    @Override
    IEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    IDatagramReader superCodec() {
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
    public IDatagramHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendnf(SocketAddress remoteAddress, byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendnf(SocketAddress remoteAddress, byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendnf(SocketAddress remoteAddress, ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendnf(SocketAddress remoteAddress, ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendnf(SocketAddress remoteAddress, IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendnf(SocketAddress remoteAddress, Object msg) {
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
}
