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

/**
 * A class with network-related utility functions.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public final class NetworkUtil {

    private NetworkUtil() {
    }

    static int digit(char c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static int hexDigit(char c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a text representation of an IPv6 address into an array of bytes.
     *
     * @param ip     the text representation of an IPv6 address
     * @param output an array for the output bytes
     * @param off    starting position for the output bytes in the array
     * @return {@code true} if the text representation was a valid IPv6 address. If
     *         the returned value is {@code false} the initial content in the output
     *         array is not changed
     */
    public static boolean ipv6ToBytes(CharSequence ip, byte[] output, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a text representation of an IPv6 address into an array of bytes.
     *
     * @param ip the text representation of an IPv6 address
     * @return an array of bytes, or {@code null} if the text representation was not
     *         a valid IPv6 address
     */
    public static byte[] ipv6ToBytes(CharSequence ip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a compressed text representation of an IPv6
     * address.
     *
     * @param data      the array of bytes
     * @param off       starting position in the array of bytes
     * @param embedIpv4 {@code true} if the IPv4 notation should should be embedded
     *                  in the returned text representation of the IPv6 address.
     * @return the text representation of the IPv6 address
     */
    public static String ipv6ToString(byte[] data, int off, boolean embedIpv4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a text representation of an IPv6 address.
     *
     * @param data      the array of bytes
     * @param off       starting position in the array of bytes
     * @param embedIpv4 {@code true} if the IPv4 notation should should be embedded
     *                  in the returned text representation of the IPv6 address
     * @param compress  {@code true} to used compressed text representation of the
     *                  IPv6 address
     *
     * @return the text representation of the IPv6 address
     */
    public static String ipv6ToString(byte[] data, int off, boolean embedIpv4, boolean compress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a text representation of an IPv4 address into an array of bytes.
     *
     * @param ip     the text representation of an IPv4 address
     * @param output an array for the output bytes
     * @param off    starting position for the output bytes in the array
     * @return {@code true} if the text representation was a valid IPv4 address. If
     *         the returned value is {@code false} the initial content in the output
     *         array is not changed
     */
    public static boolean ipv4ToBytes(CharSequence ip, byte[] output, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a text representation of an IPv4 address into an array of bytes.
     *
     * @param ip the text representation of an IPv4 address
     * @return an array of bytes, or {@code null} if the text representation was not
     *         a valid IPv4 address
     */
    public static byte[] ipv4ToBytes(CharSequence ip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a text representation of an IPv4 address.
     *
     * @param data the array of bytes
     * @param off  starting position in the array of bytes
     * @return the text representation of the IPv4 address
     */
    public static String ipv4ToString(byte[] data, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a text representation of an IPv4 address.
     *
     * @param data the array of bytes
     * @return the text representation of the IPv4 address
     */
    public static String ipv4ToString(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an {@code int} value into a text representation of an IPv4 address.
     *
     * @param value the {@code int} value
     * @return the text representation of the IPv4 address
     */
    public static String ipv4ToString(int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a text representation of an IPv4 address into an {@code int} value.
     *
     * @param ip the text representation of an IPv4 address
     * @return an {@code int} value
     */
    public static int ipv4ToInt(CharSequence ip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a port number.
     *
     * @param data the array of bytes
     * @param off  starting position in the array of bytes
     * @return the port number
     */
    public static int toPort(byte[] data, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a port number.
     *
     * @param data the array of bytes
     * @return the port number
     */
    public static int toPort(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a port number into an array of bytes.
     *
     * @param port  the port number
     * @param output an array for the output bytes
     * @param off    starting position for the output bytes in the array
     */
    public static void portToBytes(int port, byte[] output, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a port number into an array of bytes.
     *
     * @param port  the port number
     * @return an array of bytes
     */
    public static byte[] portToBytes(int port) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a {@code short} value.
     *
     * @param data the array of bytes
     * @param off  starting position in the array of bytes
     * @return the {@code short} value
     */
    public static short toShort(byte[] data, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a {@code short} value.
     *
     * @param data the array of bytes
     * @return the {@code short} value
     */
    public static short toShort(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code short} value into an array of bytes.
     *
     * @param value  the {@code short} value
     * @param output an array for the output bytes
     * @param off    starting position for the output bytes in the array
     */
    public static void toBytes(short value, byte[] output, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code short} value into an array of bytes.
     *
     * @param value the short value
     * @return an array of bytes
     */
    public static byte[] toBytes(short value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into an {@code int} value.
     *
     * @param data the array of bytes
     * @param off  starting position in the array of bytes
     * @return the {@code int} value
     */
    public static int toInt(byte[] data, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into an {@code int} value.
     *
     * @param data the array of bytes
     * @return the {@code int} value
     */
    public static int toInt(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an {@code int} value into an array of bytes.
     *
     * @param value  the short value
     * @param output an array for the output bytes
     * @param off    starting position for the output bytes in the array
     */
    public static void toBytes(int value, byte[] output, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an {@code int} value into an array of bytes.
     *
     * @param value the short value
     * @return an array of bytes
     */
    public static byte[] toBytes(int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a {@code long} value.
     *
     * @param data the array of bytes
     * @param off  starting position in the array of bytes
     * @return the {@code long} value
     */
    public static long toLong(byte[] data, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts an array of bytes into a {@code long} value.
     *
     * @param data the array of bytes
     * @return the {@code long} value
     */
    public static long toLong(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code long} value into an array of bytes.
     *
     * @param value  the short value
     * @param output an array for the output bytes
     * @param off    starting position for the output bytes in the array
     */
    public static void toBytes(long value, byte[] output, int off) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code long} value into an array of bytes.
     *
     * @param value the short value
     * @return an array of bytes
     */
    public static byte[] toBytes(long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
