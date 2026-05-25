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
package org.snf4j.example.websocket;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.snf4j.core.SSLSession;
import org.snf4j.core.codec.IBaseDecoder;
import org.snf4j.core.codec.IDecoder;
import org.snf4j.core.session.ISession;
import org.snf4j.core.session.IStreamSession;

public class IndexPageDecoder implements IDecoder<byte[], byte[]>, IBaseDecoder<byte[], byte[]> {

    final static byte CR = 13;

    final static byte LF = 10;

    final static byte[] CRLF = new byte[] { CR, LF };

    final static byte[] CRLF2 = new byte[] { CR, LF, CR, LF };

    final static String GET = "GET";

    final static String HTTP_VERSION = "HTTP/1.1";

    final static String OK = "200 OK";

    final static String BAD_REQUEST = "400 Bad Request";

    final static String FORBIDDEN = "403 Forbidden";

    final static String NOT_FOUND = "404 Not Found";

    final static String CONTENT_TYPE = "Content-Type: text/html; charset=UTF-8";

    final static String INDEX_PAGE_DECODER = "index-page-decoder";

    final static String[] INDEX_PAGE_ENDPOINTS = new String[] { "/", "/index.htm", "/index.html" };

    final static String[] WEBSOCKET_ENDPOINTS = new String[] { SessionConfig.CHAT_PATH, SessionConfig.ECHO_PATH };

    final private String host;

    static boolean matches(String value, String[] expectedValues) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IndexPageDecoder(String host) {
        this.host = host;
    }

    @Override
    public Class<byte[]> getInboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Class<byte[]> getOutboundType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(ISession session, ByteBuffer buffer, boolean flipped) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(ISession session, byte[] buffer, int off, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void response(ISession session, String status, String[] fields, String content) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void decode(ISession session, byte[] data, List<byte[]> out) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
