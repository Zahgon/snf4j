/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020-2022 SNF4J contributors
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
import java.util.concurrent.atomic.AtomicBoolean;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.handler.IDatagramHandler;
import org.snf4j.core.session.IDatagramSession;
import org.snf4j.core.session.SessionState;

class DatagramServerSession extends DatagramSession {

    private final DatagramSession delegate;

    private final SocketAddress remoteAddress;

    private AtomicBoolean isClosing = new AtomicBoolean(false);

    DatagramServerSession(DatagramSession delegate, SocketAddress remoteAddress, IDatagramHandler handler) {
        super(null, handler);
        this.delegate = delegate;
        this.remoteAddress = remoteAddress;
        setLoop(delegate.loop);
    }

    void closingFinished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    IEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IDatagramSession getParent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SessionState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void close0() {
        closeCalled.set(true);
        synchronized (writeLock) {
            if (closing != ClosingState.NONE) {
                return;
            }
            closing = ClosingState.FINISHING;
        }
        if (delegate.loop.inLoop()) {
            new ClosingTask().run();
        } else {
            delegate.loop.executenf(new ClosingTask());
        }
    }

    @Override
    void preCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void postEnding() {
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
    public SocketAddress getLocalAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketAddress getRemoteAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void suspendRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void suspendWrite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void resumeRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void resumeWrite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isReadSuspended() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isWriteSuspended() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    long superWrite(DatagramRecord record) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    IFuture<Void> superWrite(DatagramRecord record, boolean withFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void superQuickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void superClose() {
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

    private class ClosingTask implements Runnable {

        @Override
        public void run() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
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
