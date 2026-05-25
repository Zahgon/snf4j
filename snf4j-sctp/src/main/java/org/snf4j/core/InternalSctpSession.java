/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2021-2022 SNF4J contributors
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
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.snf4j.core.codec.ICodecExecutor;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.future.CancelledFuture;
import org.snf4j.core.future.FailedFuture;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.SuccessfulFuture;
import org.snf4j.core.future.TaskFuture;
import org.snf4j.core.handler.ISctpHandler;
import org.snf4j.core.handler.SctpNotificationType;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.session.ISctpSession;
import org.snf4j.core.session.ISctpSessionConfig;
import org.snf4j.core.session.IllegalSessionStateException;
import org.snf4j.core.session.SessionState;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.Notification;

abstract class InternalSctpSession extends InternalSession implements ISctpSession {

    private final SctpFragments fragments;

    private int inBufferCapacity;

    private ByteBuffer inBuffer;

    private long outQueueSize;

    private ConcurrentLinkedQueue<SctpRecord> outQueue;

    private volatile long consumedBytes;

    private final int minInBufferCapacity;

    private final int maxInBufferCapacity;

    private final ImmutableSctpMessageInfo defaultMsgInfo;

    final boolean defaultPeerAddress;

    ISctpEncodeTaskWriter encodeTaskWriter;

    InternalSctpSession(String name, ISctpHandler handler, ILogger logger) {
        super(name, handler, codec(handler), logger);
        if (codec != null) {
            ((SctpCodecExecutorAdapter) codec).setSession(this);
        }
        ISctpSessionConfig config = (ISctpSessionConfig) this.config;
        SocketAddress addr = config.getDefaultSctpPeerAddress();
        minInBufferCapacity = inBufferCapacity = config.getMinInBufferCapacity();
        maxInBufferCapacity = config.getMaxInBufferCapacity();
        fragments = new SctpFragments(allocator, minInBufferCapacity, maxInBufferCapacity, optimizeBuffers);
        if (addr == null) {
            defaultPeerAddress = false;
            defaultMsgInfo = ImmutableSctpMessageInfo.create(config.getDefaultSctpStreamNumber(), config.getDefaultSctpPayloadProtocolID(), config.getDefaultSctpUnorderedFlag());
        } else {
            defaultPeerAddress = true;
            defaultMsgInfo = ImmutableSctpMessageInfo.create(addr, config.getDefaultSctpStreamNumber(), config.getDefaultSctpPayloadProtocolID(), config.getDefaultSctpUnorderedFlag());
        }
    }

    private static SctpCodecExecutorAdapter codec(ISctpHandler handler) {
        ICodecExecutor executor;
        if (handler == null) {
            throw new IllegalArgumentException("handler is null");
        }
        executor = handler.getConfig().createCodecExecutor();
        return executor != null ? new SctpCodecExecutorAdapter(executor, handler) : null;
    }

    @Override
    ISctpEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract boolean closeNow();

    @Override
    public ISctpHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISctpSession getParent() {
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
    void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final IFuture<Void> write0(SctpRecord record, boolean withFuture) {
        SelectionKey key = checkKey(this.key);
        long futureExpectedLen;
        try {
            synchronized (writeLock) {
                key = detectRebuild(key);
                if (closing != ClosingState.NONE) {
                    return withFuture ? writeFuture(-1) : null;
                }
                outQueueSize += record.buffer.remaining();
                futureExpectedLen = outQueueSize + getConsumedBytes();
                if (withFuture) {
                    record.future = writeFuture(futureExpectedLen);
                }
                outQueue.add(record);
                setWriteInterestOps(key);
            }
        } catch (CancelledKeyException e) {
            throw new IllegalSessionStateException(SessionState.CLOSING);
        }
        lazyWakeup();
        return record.future;
    }

    ByteBuffer getInBuffer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void consumeInBuffer(MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void consumeInBufferAfterNoRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This method must be protected by the write lock.
     */
    final void consumedBytes(long number) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    long getConsumedBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ISctpReader superCodec() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ICodecPipeline getCodecPipeline(Object identifier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract Set<SocketAddress> getAddresses(Association association, boolean local);

    @Override
    public SocketAddress getLocalAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SocketAddress getRemoteAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<SocketAddress> getLocalAddresses() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<SocketAddress> getRemoteAddresses() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract void bind(InetAddress address) throws IOException;

    abstract void unbind(InetAddress address) throws IOException;

    private void bindUnbind(InetAddress address, TaskFuture<Void> future, boolean bind) {
        try {
            if (bind) {
                bind(address);
            } else {
                unbind(address);
            }
            future.success();
        } catch (Throwable t) {
            future.abort(t);
        }
    }

    private IFuture<Void> bindUnbind(final InetAddress address, final boolean bind) {
        InternalSelectorLoop loop = this.loop;
        SelectableChannel channel = this.channel;
        if (loop == null || channel == null) {
            return new CancelledFuture<Void>(this);
        }
        if (loop.inLoop()) {
            try {
                if (bind) {
                    bind(address);
                } else {
                    unbind(address);
                }
            } catch (Throwable t) {
                return new FailedFuture<Void>(this, t);
            }
            return new SuccessfulFuture<Void>(this);
        }
        final TaskFuture<Void> future = new TaskFuture<Void>(this);
        loop.executenf(new Runnable() {

            @Override
            public void run() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        });
        return future;
    }

    @Override
    public IFuture<Void> bindAddress(InetAddress address) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> unbindAddress(InetAddress address) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    HandlerResult notification(Notification notification, SctpNotificationType type) {
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

    final Queue<SctpRecord> getOutQueue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> writeFuture(long expectedLen) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private SctpRecord createRecord(ImmutableSctpMessageInfo msgInfo, byte[] msg, int offset, int length) {
        SctpRecord record = new SctpRecord(msgInfo);
        if (optimizeCopying && allocator.usesArray()) {
            record.buffer = ByteBuffer.wrap(msg, offset, length);
        } else {
            ByteBuffer buffer = allocator.allocate(length);
            buffer.put(msg, offset, length).flip();
            record.buffer = buffer;
            record.release = true;
        }
        return record;
    }

    private SctpRecord createRecord(ImmutableSctpMessageInfo msgInfo, ByteBuffer msg, int length) {
        SctpRecord record = new SctpRecord(msgInfo);
        boolean allRemaining = length == msg.remaining();
        if (optimizeCopying && allRemaining) {
            record.buffer = msg;
            record.release = optimizeBuffers;
        } else {
            ByteBuffer buf = allocator.allocate(length);
            if (allRemaining) {
                buf.put(msg).flip();
            } else {
                ByteBuffer dup = msg.duplicate();
                dup.limit(dup.position() + length);
                buf.put(dup).flip();
                msg.position(dup.position());
            }
            record.buffer = buf;
            record.release = true;
        }
        return record;
    }

    private void writenf0(ImmutableSctpMessageInfo msgInfo, byte[] msg, int offset, int length) {
        write0(createRecord(msgInfo, msg, offset, length), false);
    }

    private void writenf0(ImmutableSctpMessageInfo msgInfo, ByteBuffer msg, int length) {
        write0(createRecord(msgInfo, msg, length), false);
    }

    private IFuture<Void> write0(ImmutableSctpMessageInfo msgInfo, byte[] msg, int offset, int length) {
        return write0(createRecord(msgInfo, msg, offset, length), true);
    }

    private IFuture<Void> write0(ImmutableSctpMessageInfo msgInfo, ByteBuffer msg, int length) {
        return write0(createRecord(msgInfo, msg, length), true);
    }

    @Override
    public IFuture<Void> write(byte[] msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] msg, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] msg, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(byte[] msg, int offset, int length, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] msg, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] msg, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(byte[] msg, int offset, int length, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer msg, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer msg, int length, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer msg, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer msg, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(ByteBuffer msg, int length, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(Object msg, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IFuture<Void> write(ByteBuffer msg, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void writenf(Object msg, ImmutableSctpMessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class SctpRecord {

        final ImmutableSctpMessageInfo msgInfo;

        ByteBuffer buffer;

        boolean release;

        IFuture<Void> future;

        SctpRecord(ImmutableSctpMessageInfo msgInfo) {
            this.msgInfo = msgInfo;
        }
    }

    private class EncodeTaskWriter implements ISctpEncodeTaskWriter {

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

        @Override
        public IFuture<Void> write(ImmutableSctpMessageInfo msgInfo, ByteBuffer buffer, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IFuture<Void> write(ImmutableSctpMessageInfo msgInfo, byte[] bytes, boolean withFuture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
