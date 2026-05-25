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

import java.nio.ByteBuffer;

class Socks5InitState extends AbstractSocksState implements ISocks5 {

    final static byte METHOD_INDEX = 1;

    private final static int RESPONSE_SIZE = 2;

    private final Socks5AuthMethod[] authMethods;

    private final AbstractSocksState[] nextStates;

    Socks5InitState(Socks5ProxyHandler handler, Socks5AuthMethod[] authMethods, AbstractSocksState[] nextStates) {
        super(handler);
        this.authMethods = authMethods;
        this.nextStates = nextStates;
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
