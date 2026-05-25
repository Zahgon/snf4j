/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022 SNF4J contributors
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

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Default implementation of byte buffer holder.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class ByteBufferHolder implements IByteBufferHolder, List<ByteBuffer> {

    private final List<ByteBuffer> buffers;

    /**
     * Constructs an empty byte buffer holder with an initial capacity of ten.
     */
    public ByteBufferHolder() {
        buffers = new ArrayList<ByteBuffer>();
    }

    /**
     * Constructs an empty byte buffer holder with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity
     */
    public ByteBufferHolder(int initialCapacity) {
        buffers = new ArrayList<ByteBuffer>(initialCapacity);
    }

    /**
     * Constructs a byte buffer holder containing the byte buffers of the specified
     * collection, in the order they are returned by the collection's iterator.
     *
     * @param c the collection whose byte buffers are to be placed into this byte
     *          buffer holder
     */
    public ByteBufferHolder(Collection<ByteBuffer> c) {
        buffers = new ArrayList<ByteBuffer>(c);
    }

    @Override
    public boolean hasRemaining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int remaining() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ByteBuffer[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code false}
     */
    @Override
    public boolean isMessage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<ByteBuffer> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean add(ByteBuffer e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(Collection<? extends ByteBuffer> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(int index, Collection<? extends ByteBuffer> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ByteBuffer get(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ByteBuffer set(int index, ByteBuffer element) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void add(int index, ByteBuffer element) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ByteBuffer remove(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<ByteBuffer> listIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<ByteBuffer> listIterator(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<ByteBuffer> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
