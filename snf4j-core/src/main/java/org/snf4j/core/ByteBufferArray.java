/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022-2024 SNF4J contributors
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
package org.snf4j.core;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

/**
 * A byte buffer array wrapper providing absolute and relative get methods that
 * read data from an array of the {@link ByteBuffer} objects.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class ByteBufferArray {

    final ByteBuffer[] array;

    final int offset;

    final int size;

    final int end;

    private int index;

    private long mark = -1;

    ByteBuffer buffer;

    /**
     * Constructs a byte buffer array wrapper that will be backed by the given
     * {@link ByteBuffer} array.
     *
     * @param array The array that will back this buffer array wrapper
     */
    ByteBufferArray(ByteBuffer[] array) {
        this.array = array;
        size = end = array.length;
        offset = 0;
        if (size > 0) {
            buffer = this.array[offset];
        }
    }

    /**
     * Constructs a byte buffer array wrapper that will be backed by the given
     * {@link ByteBuffer} array.
     *
     * @param array  The array that will back this buffer array wrapper
     * @param offset The offset of the subarray to be used
     * @param length The length of the subarray to be used
     * @throws IndexOutOfBoundsException If the preconditions on the offset and
     *                                   length parameters do not hold
     */
    ByteBufferArray(ByteBuffer[] array, int offset, int length) {
        InternalSession.checkBounds(offset, length, array.length);
        this.array = array;
        if (length > 0) {
            buffer = this.array[offset];
        }
        this.offset = offset;
        this.size = length;
        end = offset + length;
        index = offset;
    }

    /**
     * Wraps an array of byte buffers into a byte buffer array wrapper that will be
     * backed by the given array.
     *
     * @param array The array that will back the returned buffer array wrapper
     * @return The new byte buffer array wrapper
     */
    public static ByteBufferArray wrap(ByteBuffer[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wraps an array of byte buffers into a byte buffer array wrapper that will be
     * backed by the given array.
     *
     * @param array  The array that will back the returned buffer array wrapper
     * @param offset The offset of the subarray to be used
     * @param length The length of the subarray to be used
     * @return The new byte buffer array wrapper
     * @throws IndexOutOfBoundsException If the preconditions on the offset and
     *                                   length parameters do not hold
     */
    public static ByteBufferArray wrap(ByteBuffer[] array, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the {@link ByteBuffer} array that backs this buffer array wrapper.
     *
     * @return The array that backs this buffer
     */
    public ByteBuffer[] array() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the offset within this buffer array wrapper's backing array of the
     * first buffer.
     *
     * @return The offset within this buffer array wrapper's array of the first
     *         buffer
     */
    public int arrayOffset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index after the last buffer in the array backing this buffer
     * array wrapper
     *
     * @return the index after the last buffer
     */
    public int arrayEnd() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the buffer in the backing array that is pointed by this
     * buffer array wrapper's current position. If this buffer array wrapper has no
     * remaining bytes the return value will equals the {@code arrayEnd()}.
     *
     * @return The index of the current buffer, or the size if this buffer array
     *         wrapper has no remaining bytes
     */
    public int arrayIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of buffers in the the backing array.
     *
     * @return The number of buffers
     */
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells whether there are any bytes between the current position and the limit.
     *
     * @return {@code true} if, and only if, there is at least one byte remaining in
     *         this buffer array wrapper
     */
    public boolean hasRemaining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells whether there are at least the {@code length} of bytes between the
     * current position and the limit.
     *
     * @param length the minimum number of bytes to check
     * @return {@code true} if, and only if, there is at least the {@code length} of
     *         bytes remaining in this buffer array wrapper
     */
    public boolean hasRemaining(long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bytes between the current position and the limit.
     *
     * @return The number of bytes remaining in this buffer array wrapper
     */
    public long remaining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns this buffer array wrapper's limit.
     *
     * @return The limit of this buffer array wrapper
     */
    public long limit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns this buffer array wrapper's position.
     *
     * @return The position of this buffer array wrapper
     */
    public long position() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets this buffer array wrapper's position. If the mark is defined and larger
     * than the new position then it is discarded.
     *
     * @param newPosition The new position value; must be non-negative and no larger
     *                    than the current limit
     * @return This buffer array wrapper
     * @throws IllegalArgumentException If the preconditions on newPosition do not
     *                                  hold
     */
    public ByteBufferArray position(long newPosition) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ByteBufferArray position(long newPosition, boolean reset) {
        if (newPosition >= 0) {
            int limit = -1, i = offset, len = end;
            long position = newPosition;
            ByteBuffer buffer = null;
            boolean positioned = false;
            for (; i < len; ++i) {
                buffer = array[i];
                limit = buffer.limit();
                if (position < limit) {
                    buffer.position((int) position);
                    index = i;
                    this.buffer = buffer;
                    if (mark > newPosition) {
                        mark = -1;
                    }
                    positioned = true;
                    break;
                } else {
                    position -= limit;
                }
            }
            if (i == len && position == 0) {
                if (limit != -1) {
                    buffer.position(limit);
                    index = --i;
                    this.buffer = buffer;
                }
                positioned = true;
            }
            if (positioned) {
                int j = offset;
                for (; j < i; ++j) {
                    array[j].position(array[j].limit());
                }
                for (++j; j < len; ++j) {
                    array[j].position(0);
                }
                return this;
            }
        }
        throw reset ? new InvalidMarkException() : new IllegalArgumentException();
    }

    /**
     * Sets this buffer array wrapper's mark at its position.
     *
     * @return This buffer array wrapper
     */
    public ByteBufferArray mark() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Resets this buffer array wrapper's position to the previously-marked
     * position.
     * <p>
     * Invoking this method neither changes nor discards the mark's value.
     *
     * @return This buffer array wrapper
     * @throws InvalidMarkException If the mark has not been set or its value is
     *                              invalid
     */
    public ByteBufferArray reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Rewinds this buffer array wrapper. The position is set to zero and the mark
     * is discarded.
     *
     * @return This buffer array wrapper
     */
    public ByteBufferArray rewind() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new byte buffer array wrapper that shares this buffer array
     * wrapper's content.
     * <p>
     * The new buffer array wrapper's limit, position, and mark values will be
     * identical to those of this buffer array wrapper and they values will be
     * changed independently.
     * <p>
     *
     * @return The new byte buffer array wrapper
     */
    public ByteBufferArray duplicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ByteBuffer buffer() {
        if (buffer == null) {
            throw new BufferUnderflowException();
        }
        while (!buffer.hasRemaining()) {
            ++index;
            if (index >= end) {
                throw new BufferUnderflowException();
            }
            buffer = array[index];
        }
        return buffer;
    }

    private ByteBuffer buffer(int size) {
        ByteBuffer b = buffer();
        if (b.remaining() >= size) {
            return b;
        }
        byte[] data = new byte[size];
        get(data);
        return ByteBuffer.wrap(data).order(b.order());
    }

    private ByteBuffer buffer(int size, long[] index) {
        long i = index[0];
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int b = offset; b < end; ++b) {
            ByteBuffer buf = array[b];
            int l = buf.limit();
            if (i >= l) {
                i -= l;
            } else if (i <= l - size) {
                index[0] = i;
                return buf;
            } else {
                byte[] data = new byte[size];
                for (int j = 0; j < size; ++j) {
                    if (i < l) {
                        data[j] = buf.get((int) i++);
                    } else if (++b < end) {
                        buf = array[b];
                        l = buf.limit();
                        i = 0;
                        --j;
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                }
                index[0] = 0;
                return ByteBuffer.wrap(data).order(buf.order());
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Relative get method that transfers bytes from this buffer array wrapper into
     * the given destination byte array.
     * <p>
     * If there are fewer bytes remaining in the buffer than are required to satisfy
     * the request then no bytes are transferred and a BufferUnderflowException is
     * thrown. Otherwise, the bytes are copied into the given array and the position
     * of this buffer array wrapper is incremented by number of copied bytes.
     *
     * @param dst the destination byte array
     * @return This buffer array wrapper
     * @throws BufferUnderflowException If there are fewer than length bytes
     *                                  remaining in this buffer buffer wrapper
     */
    public ByteBufferArray get(byte[] dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method that transfers bytes from this buffer array wrapper into
     * the given destination byte array.
     * <p>
     * If there are fewer bytes remaining in the buffer than are required to satisfy
     * the request then no bytes are transferred and a
     * {@link BufferUnderflowException} is thrown. Otherwise, the bytes are copied
     * into the given array and the position of this buffer array wrapper is
     * incremented by number of copied bytes.
     *
     * @param dst    the destination byte array
     * @param offset the offset within the array of the first byte to be copied
     * @param length the number of bytes to be copied into the given array
     * @return This buffer array wrapper
     * @throws BufferUnderflowException  If there are fewer than length bytes
     *                                   remaining in this buffer buffer wrapper
     * @throws IndexOutOfBoundsException If the preconditions on the offset and
     *                                   length parameters do not hold
     */
    public ByteBufferArray get(byte[] dst, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void checkBounds(ByteBuffer dst, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method that transfers bytes from this buffer array wrapper into
     * the given destination byte buffer.
     * <p>
     * If there are fewer bytes remaining in the buffer than are required to satisfy
     * the request then no bytes are transferred and a
     * {@link BufferUnderflowException} is thrown. Otherwise, the bytes are copied
     * into the given buffer and the position of this buffer array wrapper is
     * incremented by number of copied bytes.
     *
     * @param dst    the destination byte buffer
     * @param length the number of bytes to be copied into the given buffer
     * @return This buffer array wrapper
     * @throws BufferUnderflowException  If there are fewer than length bytes
     *                                   remaining in this buffer buffer wrapper
     * @throws IndexOutOfBoundsException If the preconditions on the length
     *                                   parameter do not hold
     */
    public ByteBufferArray get(ByteBuffer dst, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method that reads the byte at this buffer array wrapper's
     * current position, and then increments the position.
     *
     * @return The byte at the buffer array wrapper's current position
     * @throws BufferUnderflowException If the buffer array wrapper's current
     *                                  position is not smaller than its limit
     */
    public byte get() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method that reads an unsigned value of the byte at this buffer
     * array wrapper's current position, and then increments the position.
     *
     * @return An unsigned value of the byte at the buffer array wrapper's current
     *         position
     * @throws BufferUnderflowException If the buffer array wrapper's current
     *                                  position is not smaller than its limit
     */
    public int getUnsigned() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method that reads the byte at the given position.
     *
     * @param position The position from which the byte will be read
     * @return The byte at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit
     */
    public byte get(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method that reads an unsigned value of the byte at the given
     * position.
     *
     * @param position The position from which an unsigned value of the byte will be
     *                 read
     * @return An unsigned value of the byte at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit
     */
    public int getUnsigned(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading a char value.
     * <p>
     * It reads the next two bytes at this buffer array wrapper's current position,
     * composing them into a char value according to the current byte order, and
     * then increments the position by two.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The char value at the buffer array wrapper's current position
     * @throws BufferUnderflowException  If there are fewer than two bytes remaining
     *                                   in this buffer array wrapper
     */
    public char getChar() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading a char value.
     * <p>
     * It reads two bytes at the given position, composing them into a char value
     * according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The char value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus one
     */
    public char getChar(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading a short value.
     * <p>
     * It reads the next two bytes at this buffer array wrapper's current position,
     * composing them into a short value according to the current byte order, and
     * then increments the position by two.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The short value at the buffer array wrapper's current position
     * @throws BufferUnderflowException  If there are fewer than two bytes remaining
     *                                   in this buffer array wrapper
     */
    public short getShort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading an unsigned short value.
     * <p>
     * It reads the next two bytes at this buffer array wrapper's current position,
     * composing them into an unsigned short value according to the current byte
     * order, and then increments the position by two.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The unsigned short value at the buffer array wrapper's current
     *         position
     * @throws BufferUnderflowException If there are fewer than two bytes remaining
     *                                  in this buffer array wrapper
     */
    public int getUnsignedShort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading a short value.
     * <p>
     * It reads two bytes at the given position, composing them into a short value
     * according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The short value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus one
     */
    public short getShort(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading an unsigned short value.
     * <p>
     * It reads two bytes at the given position, composing them into an unsigned
     * short value according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The unsigned short value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus one
     */
    public int getUnsignedShort(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading an int value.
     * <p>
     * It reads the next four bytes at this buffer array wrapper's current position,
     * composing them into an int value according to the current byte order, and
     * then increments the position by four.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The int value at the buffer array wrapper's current position
     * @throws BufferUnderflowException If there are fewer than four bytes remaining
     *                                  in this buffer array wrapper
     */
    public int getInt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading an unsigned int value.
     * <p>
     * It reads the next four bytes at this buffer array wrapper's current position,
     * composing them into an unsigned int value according to the current byte
     * order, and then increments the position by four.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The unsigned int value at the buffer array wrapper's current position
     * @throws BufferUnderflowException If there are fewer than four bytes remaining
     *                                  in this buffer array wrapper
     */
    public long getUnsignedInt() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading an int value.
     * <p>
     * It reads four bytes at the given position, composing them into an int value
     * according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The int value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus
     *                                   three
     */
    public int getInt(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading an unsigned int value.
     * <p>
     * It reads four bytes at the given position, composing them into an unsigned
     * int value according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The unsigned int value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus
     *                                   three
     */
    public long getUnsignedInt(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading a long value.
     * <p>
     * It reads the next eight bytes at this buffer array wrapper's current position,
     * composing them into a long value according to the current byte order, and
     * then increments the position by eight.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The long value at the buffer array wrapper's current position
     * @throws BufferUnderflowException If there are fewer than eight bytes remaining
     *                                  in this buffer array wrapper
     */
    public long getLong() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading a long value.
     * <p>
     * It reads eight bytes at the given position, composing them into a long value
     * according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The long value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus
     *                                   seven
     */
    public long getLong(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading a float value.
     * <p>
     * It reads the next four bytes at this buffer array wrapper's current position,
     * composing them into a float value according to the current byte order, and
     * then increments the position by four.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The float value at the buffer array wrapper's current position
     * @throws BufferUnderflowException If there are fewer than four bytes remaining
     *                                  in this buffer array wrapper
     */
    public float getFloat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading a float value.
     * <p>
     * It reads four bytes at the given position, composing them into a float value
     * according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The float value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus
     *                                   three
     */
    public float getFloat(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Relative get method for reading a double value.
     * <p>
     * It reads the next eight bytes at this buffer array wrapper's current position,
     * composing them into a double value according to the current byte order, and
     * then increments the position by eight.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the current position of this buffer array wrapper.
     *
     * @return The double value at the buffer array wrapper's current position
     * @throws BufferUnderflowException If there are fewer than eight bytes remaining
     *                                  in this buffer array wrapper
     */
    public double getDouble() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Absolute get method for reading a double value.
     * <p>
     * It reads eight bytes at the given position, composing them into a double value
     * according to the current byte order.
     * <p>
     * NOTE: The current byte order is determined by the current byte order of the
     * buffer pointed by the given position.
     *
     * @param position The position from which the bytes will be read
     * @return The double value at the given position
     * @throws IndexOutOfBoundsException If position is negative or not smaller than
     *                                   the buffer array wrapper's limit, minus
     *                                   seven
     */
    public double getDouble(long position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class OneByteBufferArray extends ByteBufferArray {

        public OneByteBufferArray(ByteBuffer[] array) {
            super(array);
        }

        public OneByteBufferArray(ByteBuffer[] array, int off, int len) {
            super(array, off, len);
        }

        @Override
        public int arrayIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ByteBufferArray duplicate() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasRemaining() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasRemaining(long length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long remaining() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long limit() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long position() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ByteBufferArray position(long newPosition) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ByteBufferArray mark() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ByteBufferArray reset() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ByteBufferArray rewind() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ByteBufferArray get(byte[] dst) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ByteBufferArray get(byte[] dst, int off, int len) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ByteBufferArray get(ByteBuffer dst, int length) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public byte get() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public byte get(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public char getChar() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public char getChar(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public short getShort() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public short getShort(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int getInt() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int getInt(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long getLong() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public long getLong(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public float getFloat() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public float getFloat(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getDouble() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double getDouble(long position) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
