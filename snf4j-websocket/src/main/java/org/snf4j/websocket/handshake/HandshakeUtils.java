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
package org.snf4j.websocket.handshake;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.snf4j.core.util.Base64Util;

class HandshakeUtils {

    final static String SEC_WEB_SOCKET_KEY = "Sec-WebSocket-Key";

    final static String SEC_WEB_SOCKET_PROTOCOL = "Sec-WebSocket-Protocol";

    final static String SEC_WEB_SOCKET_VERSION = "Sec-WebSocket-Version";

    final static String SEC_WEB_SOCKET_ACCEPT = "Sec-WebSocket-Accept";

    final static String SEC_WEB_SOCKET_EXTENSIONS = "Sec-WebSocket-Extensions";

    final static String ORIGIN = "Origin";

    final static String UPGRADE_VALUE = "websocket";

    final static String CONNECTION_VALUE = HttpUtils.UPGRADE;

    final static int VERSION = 13;

    final static Random RANDOM = new Random();

    final static byte[] KEY_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11".getBytes(StandardCharsets.US_ASCII);

    final static String WS = "ws";

    final static String WSS = "wss";

    final static int REQUEST_LENGTH = HttpUtils.GET.length + 1 + /*URI+*/
    1 + HttpUtils.HTTP_VERSION.length + HttpUtils.CRLF.length + /*FLDS+*/
    HttpUtils.CRLF.length;

    final static int RESPONSE_LENGTH = HttpUtils.HTTP_VERSION.length + 1 + HttpUtils.STATUS_CODE_LENGTH + 1 + /*REASON+*/
    HttpUtils.CRLF.length + /*FLDS+*/
    HttpUtils.CRLF.length;

    private HandshakeUtils() {
    }

    static String generateKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String generateKey(byte[] bytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String generateAnswerKey(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static byte[] parseKey(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String requestUri(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isHttp(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isHttps(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isNotSecure(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isSecure(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static int port(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String host(URI uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static List<String> extension(String extension) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String extension(List<String> items) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
