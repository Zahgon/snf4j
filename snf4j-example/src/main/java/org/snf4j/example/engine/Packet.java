/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2019-2022 SNF4J contributors
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
package org.snf4j.example.engine;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.CRC32;
import org.snf4j.core.ByteBufferArray;

public class Packet {

    public final static int MAX_SIZE = 1024;

    public final static int HEADER_SIZE = 4;

    public final static int CHECKSUM_SIZE = 8;

    public final static int MIN_SIZE = HEADER_SIZE + CHECKSUM_SIZE;

    public final static int MAX_DATA = MAX_SIZE - MIN_SIZE;

    private static byte[] byteArray = new byte[MAX_SIZE];

    private static ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

    private final static byte[] CLOSE_DATA = "Bye!".getBytes();

    public static int calculateMaxData(int remaining) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isClose(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] getCloseData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] getBytes(ByteBuffer[] srcs, int maxSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ByteBuffer encode(int offset, ByteBuffer[] data, int maxDataSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ByteBuffer encode(int offset, byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int decodeSize(int offset, ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static byte[] decode(int offset, ByteBuffer data, int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
