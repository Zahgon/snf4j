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
import java.nio.channels.SelectionKey;
import java.util.concurrent.Executor;
import org.snf4j.core.DatagramSession.DatagramRecord;
import org.snf4j.core.future.IFuture;
import org.snf4j.core.future.ITwoThresholdFuture;
import org.snf4j.core.handler.IDatagramHandler;

class EngineDatagramWrapper {

    private final EngineDatagramHandler internal;

    private final DatagramSession session;

    private final SocketAddress remoteAddress;

    private volatile Executor executor;

    EngineDatagramWrapper(SocketAddress remoteAddress, EngineDatagramHandler internal) {
        this.session = internal.session;
        if (!session.getTimer().isSupported()) {
            if (!Constants.YES.equals(System.getProperty(Constants.IGNORE_NO_SESSION_TIMER_EXCEPTION, Constants.NO))) {
                throw new IllegalStateException("no timer specified");
            }
        }
        this.internal = internal;
        this.remoteAddress = remoteAddress;
    }

    final boolean connectedTo(SocketAddress remoteAddress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void setExecutor(Executor executor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final Executor getExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void beginHandshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void beginLazyHandshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final Object getEngineSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final IDatagramHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final IFuture<Void> write0(byte[] datagram, int offset, int length, boolean needFuture) {
        EngineDatagramRecord record = new EngineDatagramRecord(remoteAddress);
        session.initRecord(record, datagram, offset, length);
        return write0(record, needFuture);
    }

    private final IFuture<Void> write0(ByteBuffer datagram, int length, boolean needFuture) {
        EngineDatagramRecord record = new EngineDatagramRecord(remoteAddress);
        session.initRecord(record, datagram, length);
        return write0(record, needFuture);
    }

    private final IFuture<Void> write0(IByteBufferHolder datagram, boolean needFuture) {
        EngineDatagramRecord record = new EngineDatagramRecord(remoteAddress);
        session.initRecord(record, datagram);
        return write0(record, needFuture);
    }

    private final IFuture<Void> write0(EngineDatagramRecord record, boolean needFuture) {
        InternalSession.checkKey(session.key);
        if (session.closing == ClosingState.NONE) {
            return internal.write(record, needFuture);
        }
        return needFuture ? session.futuresController.getCancelledFuture() : null;
    }

    IFuture<Void> write(byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void writenf(byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> write(byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void writenf(byte[] datagram, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> write(ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void writenf(ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> write(ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void writenf(ByteBuffer datagram, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> write(IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void writenf(IByteBufferHolder datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IFuture<Void> write(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void writenf(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void quickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void dirtyClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void preCreated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void postEnding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    IEncodeTaskWriter getEncodeTaskWriter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class EngineDatagramRecord extends DatagramRecord {

        ITwoThresholdFuture<Void> future;

        EngineDatagramRecord(SocketAddress address) {
            super(address);
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
