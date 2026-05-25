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

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HttpUtils {

    final static byte CR = (byte) 13;

    final static byte LF = (byte) 10;

    final static byte SP = ' ';

    final static byte HT = '\t';

    final static byte[] CRLF = new byte[] { CR, LF };

    final static byte FS = ':';

    final static byte[] FSSP = new byte[] { FS, SP };

    final static byte[] GET = "GET".getBytes(StandardCharsets.US_ASCII);

    final static byte[] HTTP_VERSION = "HTTP/1.1".getBytes(StandardCharsets.US_ASCII);

    final static byte DIGIT_0 = '0';

    final static byte DIGIT_9 = '9';

    final static int STATUS_CODE_LENGTH = 3;

    final static String HOST = "Host";

    final static String UPGRADE = "Upgrade";

    final static String CONNECTION = "Connection";

    final static int HTTP_PORT = 80;

    final static int HTTPS_PORT = 443;

    final static String HTTP = "http";

    final static String HTTPS = "https";

    private HttpUtils() {
    }

    public static int available(byte[] data, int off, int len, int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int available(ByteBuffer data, boolean flipped, int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int splitRequestLine(byte[] data, int[] lines, int lineIndex, int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int splitResponseLine(byte[] data, int[] lines, int lineIndex, int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int splitRequestLine(byte[] data, int beginIndex, int endIndex, int[] out) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int splitHeaderField(byte[] data, int beginIndex, int endIndex, int[] tokens) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean equals(byte[] data, int[] tokens, int tokenIndex, byte[] value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean equals(byte[] data, int beginIndex, int endIndex, byte[] value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String rtrimAscii(byte[] data, int[] tokens, int tokenIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String ascii(byte[] data, int[] tokens, int tokenIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String rtrimAscii(byte[] data, int beginIndex, int endIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String ascii(byte[] data, int beginIndex, int endIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Integer digits(byte[] data, int[] tokens, int tokenIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Integer digits(byte[] data, int beginIndex, int endIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] bytes(String text) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] statusCode(int code) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String values(String[] values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> values(String values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> values(String values, String regex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
