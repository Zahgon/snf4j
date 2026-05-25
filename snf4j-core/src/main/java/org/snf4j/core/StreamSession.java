/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2017-2024 SNF4J contributors
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

import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import org.snf4j.core.allocator.IByteBufferAllocator;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.handler.IStreamHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.logger.LoggerFactory;
import org.snf4j.core.session.ISessionPipeline;
import org.snf4j.core.session.IStreamSession;
import org.snf4j.core.session.IllegalSessionStateException;
import org.snf4j.core.session.SessionState;

/**
 * The core implementation of the {@link org.snf4j.core.session.IStreamSession
 * IStreamSession} interface.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class StreamSession extends InternalSession implements IStreamSession {

    private final static ILogger LOGGER = LoggerFactory.getLogger(StreamSession.class);

    private final static ByteBuffer[] EMPTY_ARRAY = new ByteBuffer[0];

    private final ByteBuffer[] DEFAULT_ARRAY = new ByteBuffer[1];

    private ByteBuffer inBuffer;

    private ByteBuffer[] outBuffers;

    /**
     * Number of bytes in outBuffers
     */
    private long outBuffersSize;

    private final int minInBufferCapacity;

    private final int maxInBufferCapacity;

    private final int minOutBufferCapacity;

    IEncodeTaskWriter encodeTaskWriter;

    final IConsumeController consumeController = new IConsumeController() {

        @Override
        public boolean skipConsuming() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    ByteBuffer[] getInBuffersForCopying() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    int copyInBuffer(InternalSession oldSession) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    SessionPipeline<?> createPipeline() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionPipeline<IStreamSession> getPipeline() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SessionState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructs a named stream-oriented session associated with a handler.
     *
     * @param name
     *            the name for this session, or <code>null</code> if the
     *            handler's name should be used for this session's name
     * @param handler
     *            the handler that should be associated with this session
     */
    public StreamSession(String name, IStreamHandler handler) {
        super(name, handler, LOGGER);
        minInBufferCapacity = config.getMinInBufferCapacity();
        maxInBufferCapacity = config.getMaxInBufferCapacity();
        minOutBufferCapacity = config.getMinOutBufferCapacity();
    }

    @Override
    IEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructs a stream-oriented session associated with a handler.
     *
     * @param handler
     *            the handler that should be associated with this session
     */
    public StreamSession(IStreamHandler handler) {
        this(null, handler);
    }

    @Override
    public IStreamHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IStreamSession getParent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer[] clearBuffers(ByteBuffer[] outBuffers, IByteBufferAllocator allocator, boolean optimize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer[] compactBuffers(ByteBuffer[] outBuffers, IByteBufferAllocator allocator, int minOutBufferCapacity, boolean optimize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer[] putToBuffers(ByteBuffer[] outBuffers, IByteBufferAllocator allocator, ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer[] putToBuffers(ByteBuffer[] outBuffers, IByteBufferAllocator allocator, IByteBufferHolder holder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer[] putToBuffers(ByteBuffer[] outBuffers, IByteBufferAllocator allocator, int minOutBufferCapacity, Object data, int offset, int length, boolean buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer[] putToBuffers(ByteBuffer[] outBuffers, IByteBufferAllocator allocator, int minOutBufferCapacity, IByteBufferHolder holder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns -1 if session is in closing state
     */
    private final long write0(Object data, int offset, int length, boolean buffer) {
        SelectionKey key = checkKey(this.key);
        long futureExpectedLen;
        synchronized (writeLock) {
            if (closing != ClosingState.NONE) {
                return -1;
            }
            boolean optimize = buffer && optimizeBuffers;
            if (length == -1) {
                IByteBufferHolder holder = (IByteBufferHolder) data;
                if (outBuffers.length == 0) {
                    outBuffers = DEFAULT_ARRAY;
                    outBuffers[0] = optimize ? null : allocator.allocate(minOutBufferCapacity);
                }
                length = holder.remaining();
                if (optimize) {
                    outBuffers = putToBuffers(outBuffers, allocator, holder);
                } else {
                    outBuffers = putToBuffers(outBuffers, allocator, minOutBufferCapacity, holder);
                }
            } else if (optimize && ((ByteBuffer) data).remaining() == length) {
                if (outBuffers.length == 0) {
                    outBuffers = DEFAULT_ARRAY;
                    outBuffers[0] = null;
                }
                outBuffers = putToBuffers(outBuffers, allocator, (ByteBuffer) data);
            } else {
                if (outBuffers.length == 0) {
                    outBuffers = DEFAULT_ARRAY;
                    outBuffers[0] = allocator.allocate(minOutBufferCapacity);
                }
                outBuffers = putToBuffers(outBuffers, allocator, minOutBufferCapacity, data, offset, length, buffer);
            }
            outBuffersSize += length;
            futureExpectedLen = outBuffersSize + getWrittenBytes();
            try {
                setWriteInterestOps(detectRebuild(key));
            } catch (CancelledKeyException e) {
                throw new IllegalSessionStateException(SessionState.CLOSING);
            }
        }
        lazyWakeup();
        return futureExpectedLen;
    }

    final long write0(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final long write0(IByteBufferHolder holder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IFuture<Void> write1(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IFuture<Void> write1(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IFuture<Void> write1(IByteBufferHolder holder) {
        throw new UnsupportedOperationException("STUB: not implemented");
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

    @Override
    public IFuture<Void> write(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer data) {
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
    public IFuture<Void> write(ByteBuffer data, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer data, int length) {
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
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void superEvent(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void event(SessionEvent event) {
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

    /**
     * Gets the buffer into which received data should be written.
     * @return buffer in the write mode (i.e. not flipped yet).
     */
    ByteBuffer getInBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the buffers with data ready to be sent.
     * Any modification done to the returned buffers should be executed
     * inside synchronized block on the object returned from the method
     * getOutBuffersLock. After modification is done the method compactOutBuffers
     * should be executed inside the same synchronized block to compact the buffers.
     * @return buffers in the read mode (i.e. flipped).
     * @see getOutBuffersLock
     */
    ByteBuffer[] getOutBuffers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ByteBuffer consumeBuffer(ByteBuffer inBuffer, IStreamReader handler, IByteBufferAllocator allocator, IConsumeController consumeController) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void consumeBuffer(ByteBuffer inBuffer, IStreamReader handler, IConsumeController consumeController) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Informs that input buffer has new data that may be ready to consume.
     */
    @Override
    void consumeInBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void consumeInBufferAfterNoRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IStreamReader superCodec() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Informs that some data was removed from output buffers.
     * It should be executed inside synchronized block on the object returned
     * from the method getOutBuffersLock
     * @param consumedBytes number of bytes consumed from the buffers
     * @return true if there is no data left in the buffers
     * @see getOutBuffersLock
     */
    boolean compactOutBuffers(long consumedBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final Socket getSocket() {
        SelectableChannel channel = this.channel;
        if (channel instanceof SocketChannel && channel.isOpen()) {
            return ((SocketChannel) channel).socket();
        }
        return null;
    }

    @Override
    public SocketAddress getLocalAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketAddress getRemoteAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
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
