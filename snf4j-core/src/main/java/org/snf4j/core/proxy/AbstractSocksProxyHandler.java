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
package org.snf4j.core.proxy;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.session.IStreamSession;

/**
 * Base implementation for handlers processing client connections via SOCKS
 * proxy protocols.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
abstract public class AbstractSocksProxyHandler extends AbstractProxyHandler {

    private final InetSocketAddress address;

    private final List<ISocksReply> replies = new ArrayList<ISocksReply>(2);

    private List<ISocksReplyListener> replyListeners;

    volatile AbstractSocksState state;

    /**
     * Constructs a SOCKS proxy connection handler with the specified destination
     * address, the default (10 seconds) connection timeout, configuration and factory.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param address           the destination address
     * @param config            the session configuration object, or {@code null} to
     *                          use the default configuration
     * @param factory           the factory that will be used to configure the
     *                          internal structure of the associated session, or
     *                          {@code null} to use the default factory
     * @throws IllegalArgumentException if the address is null
     */
    protected AbstractSocksProxyHandler(InetSocketAddress address, ISessionConfig config, ISessionStructureFactory factory) {
        super(config, factory);
        checkNull(address, "address");
        this.address = address;
    }

    /**
     * Returns the destination address.
     *
     * @return the destination address
     */
    public InetSocketAddress getAddress() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns replies sent from the SOCKS server.
     *
     * @return the replies sent from the SOCKS server
     */
    public ISocksReply[] getReplies() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds a listener for replies sent from the SOCKS server
     *
     * @param listener a listener for replies
     */
    public void addReplyListener(ISocksReplyListener listener) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int reply(ISocksReply reply) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void flipAndWrite(ByteBuffer buf) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(ByteBuffer data, boolean flipped) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(byte[] data, int off, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void handleReady() throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    abstract String protocol();
}
