/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2021 SNF4J contributors
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
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;

/**
 * An immutable implementation of the {@code com.sun.nio.sctp.MessageInfo}.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public abstract class ImmutableSctpMessageInfo extends MessageInfo {

    abstract MessageInfo unwrap();

    private ImmutableSctpMessageInfo() {
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified stream number and
     * the peer primary address as the preferred peer address.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, its {@code complete}
     * value set to {@code true}, and its {@code payloadProtocolID} value set to 0.
     *
     * @param streamNumber the stream number that the message will be sent on
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(int streamNumber) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified stream number and
     * the preferred peer address.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, its {@code complete}
     * value set to {@code true}, and its {@code payloadProtocolID} value set to 0.
     *
     * @param address      the preferred peer address of the association to send the
     *                     message to, or null to use the peer primary address
     * @param streamNumber the stream number that the message will be sent on
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(SocketAddress address, int streamNumber) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified association, stream
     * number, and the peer primary address as the preferred peer address of the
     * association to send the message to.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, its {@code complete}
     * value set to {@code true}, and its {@code payloadProtocolID} value set to 0.
     *
     * @param association  the association to send the message on
     * @param streamNumber the stream number that the message will be sent on
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(Association association, int streamNumber) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified association, stream
     * number and the preferred peer address of the association to send the message
     * to.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, its {@code complete}
     * value set to {@code true}, and its {@code payloadProtocolID} value set to 0.
     *
     * @param association  the association to send the message on
     * @param address      the preferred peer address of the association to send the
     *                     message to, or null to use the peer primary address
     * @param streamNumber the stream number that the message will be sent on
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(Association association, SocketAddress address, int streamNumber) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified stream number,
     * payload protocol identifier and the peer primary address as the
     * preferred peer address.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, and its
     * {@code complete} value set to {@code true}.
     *
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(int streamNumber, int payloadProtocolID) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified stream number,
     * payload protocol identifier and the preferred peer address.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, and its
     * {@code complete} value set to {@code true}.
     *
     * @param address           the preferred peer address of the association to
     *                          send the message to, or null to use the peer primary
     *                          address
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(SocketAddress address, int streamNumber, int payloadProtocolID) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified association, stream
     * number, payload protocol identifier and the peer primary address as the
     * preferred peer address of the association to send the message to.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, and its
     * {@code complete} value set to {@code true}.
     *
     * @param association       the association to send the message on
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(Association association, int streamNumber, int payloadProtocolID) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified association, stream
     * number, payload protocol identifier and the preferred peer address of the
     * association to send the message to.
     * <p>
     * The returned instance will have its {@code unordered} flag set to
     * {@code false}, its {@code timeToLive} value set to 0, and its
     * {@code complete} value set to {@code true}.
     *
     * @param association       the association to send the message on
     * @param address           the preferred peer address of the association to
     *                          send the message to, or null to use the peer primary
     *                          address
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(Association association, SocketAddress address, int streamNumber, int payloadProtocolID) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified stream number,
     * payload protocol identifier, unordered flag and the peer primary address as
     * the preferred peer address.
     * <p>
     * The returned instance will have its {@code timeToLive} value set to 0, and
     * its {@code complete} value set to {@code true}.
     *
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @param unordered         {@code true} requests the un-ordered delivery of the
     *                          message, {@code false} indicates that the message is
     *                          ordered.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(int streamNumber, int payloadProtocolID, boolean unordered) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified stream number,
     * payload protocol identifier, unordered flag and the preferred peer address.
     * <p>
     * The returned instance will have its {@code timeToLive} value set to 0, and
     * its {@code complete} value set to {@code true}.
     *
     * @param address           the preferred peer address of the association to
     *                          send the message to, or null to use the peer primary
     *                          address
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @param unordered         {@code true} requests the un-ordered delivery of the
     *                          message, {@code false} indicates that the message is
     *                          ordered.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(SocketAddress address, int streamNumber, int payloadProtocolID, boolean unordered) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified association, stream
     * number, payload protocol identifier, unordered flag and the peer primary
     * address as the preferred peer address of the association to send the message
     * to.
     * <p>
     * The returned instance will have its {@code timeToLive} value set to 0, and
     * its {@code complete} value set to {@code true}.
     *
     * @param association       the association to send the message on
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @param unordered         {@code true} requests the un-ordered delivery of the
     *                          message, {@code false} indicates that the message is
     *                          ordered.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(Association association, int streamNumber, int payloadProtocolID, boolean unordered) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} with specified association, stream
     * number, payload protocol identifier, unordered flag and the preferred peer
     * address of the association to send the message to.
     * <p>
     * The returned instance will have its {@code timeToLive} value set to 0, and
     * its {@code complete} value set to {@code true}.
     *
     * @param association       the association to send the message on
     * @param address           the preferred peer address of the association to
     *                          send the message to, or null to use the peer primary
     *                          address
     * @param streamNumber      the stream number that the message will be sent on
     * @param payloadProtocolID The payload protocol identifier.
     * @param unordered         {@code true} requests the un-ordered delivery of the
     *                          message, {@code false} indicates that the message is
     *                          ordered.
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(Association association, SocketAddress address, int streamNumber, int payloadProtocolID, boolean unordered) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an immutable {@code MessageInfo} that is cloned from the specified
     * {@code MessageInfo} instance.
     *
     * @param msgInfo the instance from which the immutable {@code MessageInfo}
     *                should be cloned
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo create(MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wraps the specified {@code MessageInfo} instance.
     * <p>
     * <b>Caution:</b> The return instance is not immutable and have to be handled
     * carefully after passing it to {@link org.snf4j.core.session.ISctpSession
     * ISctpSession}'s write methods. The internal state of the wrapped instance
     * should not be change as it may affect the result of the write
     * methods.
     *
     * @param msgInfo the {@code MessageInfo} instance to be wrapped
     * @return the immutable {@code MessageInfo}
     */
    public static ImmutableSctpMessageInfo wrap(MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static class OutgoingMessageInfo extends ImmutableSctpMessageInfo {

        SocketAddress address;

        Association association;

        boolean complete = true;

        boolean unordered;

        int payloadProtocolID;

        int streamNumber;

        long timeToLive;

        private OutgoingMessageInfo(SocketAddress address, int streamNumber) {
            this.address = address;
            this.streamNumber = streamNumber;
        }

        private OutgoingMessageInfo(SocketAddress address, int streamNumber, int payloadProtocolID) {
            this.address = address;
            this.streamNumber = streamNumber;
            this.payloadProtocolID = payloadProtocolID;
        }

        private OutgoingMessageInfo(SocketAddress address, int streamNumber, int payloadProtocolID, boolean unordered) {
            this.address = address;
            this.streamNumber = streamNumber;
            this.payloadProtocolID = payloadProtocolID;
            this.unordered = unordered;
        }

        private OutgoingMessageInfo(Association association, SocketAddress address, int streamNumber) {
            this.association = association;
            this.address = address;
            this.streamNumber = streamNumber;
        }

        private OutgoingMessageInfo(Association association, SocketAddress address, int streamNumber, int payloadProtocolID) {
            this.association = association;
            this.address = address;
            this.streamNumber = streamNumber;
            this.payloadProtocolID = payloadProtocolID;
        }

        private OutgoingMessageInfo(Association association, SocketAddress address, int streamNumber, int payloadProtocolID, boolean unordered) {
            this.association = association;
            this.address = address;
            this.streamNumber = streamNumber;
            this.payloadProtocolID = payloadProtocolID;
            this.unordered = unordered;
        }

        @Override
        public SocketAddress address() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Association association() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int bytes() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo complete(boolean arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isComplete() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isUnordered() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int payloadProtocolID() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo payloadProtocolID(int arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int streamNumber() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo streamNumber(int arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long timeToLive() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo timeToLive(long arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo unordered(boolean arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        MessageInfo unwrap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static class WrappingMessageInfo extends ImmutableSctpMessageInfo {

        private final MessageInfo msgInfo;

        private WrappingMessageInfo(MessageInfo msgInfo) {
            this.msgInfo = msgInfo;
        }

        @Override
        public SocketAddress address() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Association association() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isComplete() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isUnordered() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int streamNumber() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int payloadProtocolID() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long timeToLive() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        MessageInfo unwrap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int bytes() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo complete(boolean arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo payloadProtocolID(int arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo streamNumber(int arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo timeToLive(long arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public MessageInfo unordered(boolean arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
