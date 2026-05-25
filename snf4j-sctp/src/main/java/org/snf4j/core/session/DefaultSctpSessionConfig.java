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
package org.snf4j.core.session;

import java.net.SocketAddress;
import org.snf4j.core.codec.ICodecExecutor;
import com.sun.nio.sctp.MessageInfo;

/**
 * Default configuration for the SCTP session.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class DefaultSctpSessionConfig extends DefaultSessionConfig implements ISctpSessionConfig {

    private int defaultSctpStreamNumber;

    /* 0 */
    private int defaultSctpPayloadProtocolID;

    /* 0 */
    private boolean defaultSctpUnorderedFlag;

    /* false */
    private SocketAddress defaultSctpPeerAddress;

    /* null */
    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@link #DEFAULT_CODEC_EXECUTOR_IDENTIFIER}
     */
    @Override
    public Object getCodecExecutorIdentifier(MessageInfo msgInfo) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is <code>null</code>
     */
    @Override
    public ICodecExecutor createCodecExecutor(Object identifier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is 0
     */
    @Override
    public int getDefaultSctpStreamNumber() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures the stream number for the SCTP messages sent by the
     * {@link org.snf4j.core.session.ISctpSession ISctpSession}'s write methods
     * without specified the {@code msgInfo} argument.
     *
     * @param defaultStreamNumber the default stream number
     * @return this session config object
     */
    public DefaultSctpSessionConfig setDefaultSctpStreamNumber(int defaultStreamNumber) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is 0
     */
    @Override
    public int getDefaultSctpPayloadProtocolID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures the payload protocol identifier for the SCTP messages sent by the
     * {@link org.snf4j.core.session.ISctpSession ISctpSession}'s write methods
     * without specified the {@code msgInfo} argument.
     *
     * @param defaultPayloadProtocolID the default payload protocol identifier
     * @return this session config object
     */
    public DefaultSctpSessionConfig setDefaultSctpPayloadProtocolID(int defaultPayloadProtocolID) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code false}
     */
    @Override
    public boolean getDefaultSctpUnorderedFlag() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures the unordered flag for the SCTP messages sent by the
     * {@link org.snf4j.core.session.ISctpSession ISctpSession}'s write methods
     * without specified the {@code msgInfo} argument.
     *
     * @param defaultUnorderedFlag the default unordered flag
     * @return this session config object
     */
    public DefaultSctpSessionConfig setDefaultSctpUnorderedFlag(boolean defaultUnorderedFlag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code null}
     */
    @Override
    public SocketAddress getDefaultSctpPeerAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures the preferred peer address for the SCTP messages sent by the
     * {@link org.snf4j.core.session.ISctpSession ISctpSession}'s write methods
     * without specified the {@code msgInfo} argument.
     *
     * @param defaultPeerAddress the preferred peer address, or {@code null} to use
     *                           the peer primary address
     * @return this session config object
     */
    public DefaultSctpSessionConfig setDefaultSctpPeerAddress(SocketAddress defaultPeerAddress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
