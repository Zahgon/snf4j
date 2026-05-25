/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2017-2022 SNF4J contributors
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

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.IDatagramHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.logger.LoggerFactory;
import org.snf4j.core.session.IDatagramSession;
import org.snf4j.core.session.IllegalSessionStateException;
import org.snf4j.core.session.SessionState;

/**
 * The core implementation of the {@link org.snf4j.core.session.IDatagramSession
 * IDatagramSession} interface.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class DatagramSession extends InternalSession implements IDatagramSession {

    private final static ILogger LOGGER = LoggerFactory.getLogger(DatagramSession.class);

    private ByteBuffer inBuffer;

    private int inBufferCapacity;

    private ConcurrentLinkedQueue<DatagramRecord> outQueue;

    /**
     * Number of bytes in the queue
     */
    private long outQueueSize;

    private final int minInBufferCapacity;

    private final int maxInBufferCapacity;

    private final boolean ignorePossiblyIncomplete;

    IEncodeTaskWriter encodeTaskWriter;

    /**
     * Constructs a named datagram-oriented session associated with a handler.
     *
     * @param name
     *            the name for this session, or <code>null</code> if the
     *            handler's name should be used for this session's name
     * @param handler
     *            the handler that should be associated with this session
     */
    public DatagramSession(String name, IDatagramHandler handler) {
        super(name, handler, LOGGER);
        minInBufferCapacity = inBufferCapacity = config.getMinInBufferCapacity();
        maxInBufferCapacity = config.getMaxInBufferCapacity();
        ignorePossiblyIncomplete = config.ignorePossiblyIncompleteDatagrams();
    }

    IEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    SessionPipeline<?> createPipeline() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructs a datagram-oriented session associated with a handler.
     *
     * @param handler
     *            the handler that should be associated with this session
     */
    public DatagramSession(IDatagramHandler handler) {
        this(null, handler);
    }

    private final DatagramSocket getSocket() {
        SelectableChannel channel = this.channel;
        if (channel instanceof DatagramChannel && channel.isOpen()) {
            return ((DatagramChannel) channel).socket();
        }
        return null;
    }

    void event(SocketAddress remoteAddress, DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IDatagramHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IDatagramSession getParent() {
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

    long superWrite(DatagramRecord record) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> superWrite(DatagramRecord record, boolean withFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final long write0(DatagramRecord record) {
        SelectionKey key = checkKey(this.key);
        long futureExpectedLen;
        try {
            synchronized (writeLock) {
                key = detectRebuild(key);
                if (closing != ClosingState.NONE) {
                    return -1;
                }
                outQueueSize += record.holder.remaining();
                futureExpectedLen = outQueueSize + getWrittenBytes();
                outQueue.add(record);
                setWriteInterestOps(key);
            }
        } catch (CancelledKeyException e) {
            throw new IllegalSessionStateException(SessionState.CLOSING);
        }
        lazyWakeup();
        return futureExpectedLen;
    }

    final DatagramRecord initRecord(DatagramRecord record, byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final DatagramRecord initRecord(DatagramRecord record, ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final DatagramRecord initRecord(DatagramRecord record, IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final long write0(DatagramRecord record, byte[] datagram, int offset, int length) {
        return write0(initRecord(record, datagram, offset, length));
    }

    private final long write0(DatagramRecord record, ByteBuffer datagram, int length) {
        return write0(initRecord(record, datagram, length));
    }

    private final long write0(DatagramRecord record, IByteBufferHolder datagram) {
        return write0(initRecord(record, datagram));
    }

    private IFuture<Void> write1(DatagramRecord record) {
        long futureExpectedLen = write0(record);
        if (futureExpectedLen == -1) {
            return futuresController.getCancelledFuture();
        }
        return futuresController.getWriteFuture(futureExpectedLen);
    }

    @Override
    public IFuture<Void> write(byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> simpleSend(SocketAddress remoteAddress, byte[] datagram, boolean withFuture) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> send(SocketAddress remoteAddress, byte[] datagram) {
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

    IFuture<Void> simpleSend(SocketAddress remoteAddress, ByteBuffer datagram, boolean withFuture) {
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

    IFuture<Void> simpleSend(SocketAddress remoteAddress, IByteBufferHolder datagram, boolean withFuture) {
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

    private final void close(SelectionKey key) throws IOException {
        close(key.channel());
        loop.finishInvalidatedKey(key);
    }

    @Override
    void close(SelectableChannel channel) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void close0() {
        SelectionKey key = this.key;
        closeCalled.set(true);
        if (key != null && key.isValid()) {
            try {
                synchronized (writeLock) {
                    key = detectRebuild(key);
                    if (closing == ClosingState.NONE) {
                        if ((key.interestOps() & SelectionKey.OP_WRITE) != 0) {
                            closing = ClosingState.SENDING;
                        } else {
                            closing = ClosingState.FINISHED;
                            close(key);
                        }
                    }
                }
            } catch (Exception e) {
            }
        } else {
            quickClose();
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void quickClose0() {
        SelectionKey key = this.key;
        closeCalled.set(true);
        if (key != null && key.isValid()) {
            try {
                synchronized (writeLock) {
                    key = detectRebuild(key);
                    closing = ClosingState.FINISHED;
                    close(key);
                }
            } catch (Exception e) {
            }
        } else if (channel != null) {
            try {
                close(channel);
            } catch (IOException e) {
            }
        }
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

    final void superEvent(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This method must be protected by the write lock.
     */
    final void consumedBytes(long number) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Handles closing operation being in progress. It should be executed only
     * when the output queue have no more data after writing.
     */
    void handleClosingInProgress() {
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

    final Queue<DatagramRecord> getOutQueue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ByteBuffer getInBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void consumeInBuffer(SocketAddress remoteAddress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IDatagramReader superCodec() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class DatagramRecord {

        SocketAddress address;

        IByteBufferHolder holder;

        boolean release;

        DatagramRecord(SocketAddress address) {
            this.address = address;
        }
    }

    private class EncodeTaskWriter implements IEncodeTaskWriter {

        @Override
        public final IFuture<Void> write(SocketAddress remoteAddress, ByteBuffer buffer, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public final IFuture<Void> write(SocketAddress remoteAddress, byte[] bytes, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IFuture<Void> write(SocketAddress remoteAddress, IByteBufferHolder holder, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
