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
package org.snf4j.core.util;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * A class with Base64 utility functions.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public final class Base64Util {

    private final static byte[] EMPTY = new byte[0];

    private final static byte PAD = (byte) '=';

    private final static int PAD_INDEX = 64;

    private final static char[] ALPHABET = new char[('Z' - 'A' + 1) * 2 + 10 + 2 + 1];

    private final static int[] DECODING = new int[256];

    static {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            ALPHABET[i++] = c;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            ALPHABET[i++] = c;
        }
        for (char c = '0'; c <= '9'; c++) {
            ALPHABET[i++] = c;
        }
        ALPHABET[i++] = '+';
        ALPHABET[i++] = '/';
        ALPHABET[i++] = PAD;
        Arrays.fill(DECODING, (byte) -1);
        for (i = 0; i < ALPHABET.length - 1; ++i) {
            DECODING[ALPHABET[i]] = (byte) i;
        }
    }

    private Base64Util() {
    }

    /**
     * Encodes bytes from the specified byte array into a newly-allocated byte array
     * using the Base64 encoding scheme.
     * <p>
     * It uses "The Base 64 Alphabet" as specified in Table 1 of RFC 4648.
     *
     * @param data the byte array to encode
     * @return A newly-allocated byte array containing the resulting encoded bytes
     */
    public static byte[] encode(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Encodes the specified byte array into a String using the Base64 encoding
     * scheme.
     * <p>
     * It first encodes input bytes into a base64 encoded byte array by calling the
     * {@link #encode(byte[])} method and then constructs a new String by using the
     * encoded byte array and the specified charset.
     *
     * @param data    the byte array to encode
     * @param charset the charset used to encode the resulting String
     * @return A String containing the resulting Base64 encoded characters
     */
    public static String encode(byte[] data, Charset charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Encodes bytes from the specified byte array into a newly-allocated byte array
     * using the Base64 encoding scheme.
     * <p>
     * It uses "The Base 64 Alphabet" as specified in Table 1 of RFC 4648.
     *
     * @param data   the byte array to encode
     * @param offset offset within the array of the first byte to be encoded
     * @param length number of bytes to be encoded
     * @return A newly-allocated byte array containing the resulting encoded bytes
     */
    public static byte[] encode(byte[] data, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Encodes the specified byte array into a String using the Base64 encoding
     * scheme.
     * <p>
     * It first encodes input bytes into a base64 encoded byte array by calling the
     * {@link #encode(byte[])} method and then constructs a new String by using the
     * encoded byte array and the specified charset.
     *
     * @param data    the byte array to encode
     * @param offset  offset within the array of the first byte to be encoded
     * @param length  number of bytes to be encoded
     * @param charset the charset used to encode the resulting String
     * @return A String containing the resulting Base64 encoded characters
     */
    public static String encode(byte[] data, int offset, int length, Charset charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decodes bytes from the specified byte array into a newly-allocated byte array
     * using the Base64 encoding scheme.
     * <p>
     * It uses "The Base 64 Alphabet" as specified in Table 1 of RFC 4648.
     *
     * @param data the byte array to decode
     * @return A newly-allocated byte array containing the resulting decoded bytes
     * @throws IllegalArgumentException - if the data is not in valid Base64 scheme
     */
    public static byte[] decode(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decodes bytes from the specified byte array into a newly-allocated byte array
     * using the Base64 encoding scheme with an option for the MIME format.
     * <p>
     * It uses "The Base 64 Alphabet" as specified in Table 1 of RFC 4648.
     *
     * @param data   the byte array to decode
     * @param isMime {@code true} if the data is encoded in the MIME format
     * @return A newly-allocated byte array containing the resulting decoded bytes,
     *         or {@code null} if the data is not in valid Base64 scheme
     */
    public static byte[] decode(byte[] data, boolean isMime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decodes a Base64 encoded String into a newly-allocated byte array using the
     * Base64 encoding scheme.
     * <p>
     * It first decodes the Base64 encoded String into a sequence of bytes using the
     * given charset and then decode the bytes by calling the
     * {@link #decode(byte[])} method.
     *
     * @param data    the string to decode
     * @param charset The charset to be used to encode the String
     * @return A newly-allocated byte array containing the resulting decoded bytes,
     *         or {@code null} if the data is not in valid Base64 scheme
     */
    public static byte[] decode(String data, Charset charset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decodes a Base64 encoded String into a newly-allocated byte array using the
     * Base64 encoding scheme with an option for the MIME format.
     * <p>
     * It first decodes the Base64 encoded String into a sequence of bytes using the
     * given charset and then decode the bytes by calling the
     * {@link #decode(byte[])} method.
     *
     * @param data    the string to decode
     * @param isMime  {@code true} if the data is encoded in the MIME format
     * @param charset The charset to be used to encode the String
     * @return A newly-allocated byte array containing the resulting decoded bytes,
     *         or {@code null} if the data is not in valid Base64 scheme
     */
    public static byte[] decode(String data, Charset charset, boolean isMime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decodes bytes from the specified byte array into a newly-allocated byte array
     * using the Base64 encoding scheme with an option for the MIME format.
     * <p>
     * It uses "The Base 64 Alphabet" as specified in Table 1 of RFC 4648.
     *
     * @param data   the byte array to decode
     * @param offset offset within the array of the first byte to be decoded
     * @param length number of bytes to be encoded
     * @param isMime {@code true} if the data is encoded in the MIME format
     * @return A newly-allocated byte array containing the resulting decoded bytes,
     *         or {@code null} if the data is not in valid Base64 scheme
     */
    public static byte[] decode(byte[] data, int offset, int length, boolean isMime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
