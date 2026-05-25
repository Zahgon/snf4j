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
package org.snf4j.websocket.frame;

class Utf8 {

    private static final int ACCEPT = 0;

    private static final int REJECT = 12;

    private static final int[] TYPES = { // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 00..1f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 20..3f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 40..5f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 60..7f
    0, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    1, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // 80..9f
    9, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // a0..bf
    7, // c0..df
    8, // c0..df
    8, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // c0..df
    2, // e0..ef
    0xa, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x3, // e0..ef
    0x4, // e0..ef
    0x3, // e0..ef
    0x3, // f0..ff
    0xb, // f0..ff
    0x6, // f0..ff
    0x6, // f0..ff
    0x6, // f0..ff
    0x5, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8, // f0..ff
    0x8 };

    private static final int[] STATES = { 0, 12, 24, 36, 60, 96, 84, 12, 12, 12, 48, 72, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 0, 12, 12, 12, 12, 12, 0, 12, 0, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 36, 12, 36, 12, 12, 12, 36, 12, 12, 12, 12, 12, 36, 12, 36, 12, 12, 12, 36, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };

    private Utf8() {
    }

    /**
     * Check if the provided BytebBuffer contains a valid utf8 encoded string.
     * <p>
     * Using the algorithm "Flexible and Economical UTF-8 Decoder" by Bjoern Hoehrmann
     * (http://bjoern.hoehrmann.de/utf-8/decoder/dfa/)
     */
    static boolean isValid(byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isValid(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean validate(ValidationContext ctx, byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isValid(ValidationContext ctx) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class ValidationContext {

        private int state;

        private int codep;
    }
}
