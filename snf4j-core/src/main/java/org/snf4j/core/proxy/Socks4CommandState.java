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
import java.nio.charset.StandardCharsets;
import org.snf4j.core.util.NetworkUtil;

class Socks4CommandState extends AbstractSocksState implements ISocks4 {

    private final static byte REPLY_VERSION = 0;

    private final static int RESPONSE_SIZE = 8;

    private final static byte[] DOMAIN_MARKER = new byte[] { 0, 0, 0, 1 };

    final static int STATUS_INDEX = 1;

    final static int IP_INDEX = 4;

    final static int PORT_INDEX = 2;

    private final Socks4Command command;

    private final String username;

    Socks4CommandState(Socks4ProxyHandler handler, Socks4Command command, String username) {
        super(handler);
        this.command = command;
        this.username = username == null ? "" : username;
    }

    @Override
    int responseSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    AbstractSocksState read(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void handleReady() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
