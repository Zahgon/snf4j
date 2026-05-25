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

class Socks5CommandState extends AbstractSocksState implements ISocks5 {

    final static int STATUS_INDEX = 1;

    final static int ATYP_INDEX = 3;

    final static int ADDR_INDEX = 4;

    private final Socks5Command command;

    Socks5CommandState(Socks5ProxyHandler handler, Socks5Command command) {
        super(handler);
        this.command = command;
    }

    private int expectedLength(byte atyp, byte a0) {
        int alen;
        switch(atyp) {
            case 1:
                alen = 4;
                break;
            case 3:
                alen = 1 + ((int) a0 & 0xff);
                break;
            case 4:
                alen = 16;
                break;
            default:
                //unexpected address type, set to 0 and fail during parsing
                alen = 0;
        }
        return alen + 6;
    }

    @Override
    int available(ByteBuffer data, boolean flipped) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    int available(byte[] data, int off, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
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
