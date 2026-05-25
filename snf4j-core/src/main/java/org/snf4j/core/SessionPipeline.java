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
package org.snf4j.core;

import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

class SessionPipeline<T extends InternalSession> {

    private final static Item<?>[] EMPTY = new Item<?>[0];

    private final T owner;

    private final LinkedList<Item<T>> items = new LinkedList<Item<T>>();

    private int itemsVersion;

    private int version;

    private Item<T> first;

    private Item<T> last;

    private volatile boolean eos;

    private volatile Throwable cause;

    private volatile boolean undone;

    private volatile Throwable undoneCause;

    SessionPipeline(T owner) {
        this.owner = owner;
    }

    void sync(Item<T> current) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    T first() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private int indexOf(Object key) {
        int index = 0;
        for (Iterator<Item<T>> i = items.iterator(); i.hasNext(); ) {
            if (i.next().key.equals(key)) {
                return index;
            }
            ++index;
        }
        throw new NoSuchElementException("key '" + key + "' does not exist");
    }

    private void notExists(Object key) {
        for (Iterator<Item<T>> i = items.iterator(); i.hasNext(); ) {
            if (i.next().key.equals(key)) {
                throw new IllegalArgumentException("key '" + key + "' already exists");
            }
        }
    }

    private void notExists(T session, Item<T> ignore) {
        if (session == owner) {
            throw new IllegalArgumentException("session is owner");
        }
        for (Iterator<Item<T>> i = items.iterator(); i.hasNext(); ) {
            Item<T> item = i.next();
            if (item != ignore && item.session == session) {
                throw new IllegalArgumentException("session already exists");
            }
        }
    }

    private void notNull(Object argument, String argName) {
        if (argument == null) {
            throw new NullPointerException(argName + " is null");
        }
    }

    private Item<T> item(Object key, T session) {
        notExists(key);
        return new Item<T>(key, session, this);
    }

    void addFirst(Object key, T session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void addAfter(Object baseKey, Object key, T session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void add(Object key, T session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void addBefore(Object baseKey, Object key, T session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    T replace(Object oldKey, Object key, T session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T remove(Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T get(Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T getOwner() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<Object> getKeys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void markClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void markClosed(Throwable cause) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void markUndone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void markUndone(Throwable cause) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void markDone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void close0(InternalSession current, StoppingType type) {
        switch(type) {
            case GENTLE:
                current.close();
                break;
            case QUICK:
                current.quickClose();
                break;
            case DIRTY:
                current.dirtyClose();
                break;
        }
    }

    private void close0(StoppingType type) {
        Item<T> item = first;
        T current = owner;
        while (item != null) {
            T session = item.session();
            SelectionKey key = session.key;
            if (key != null && ((ChannelContext<?>) key.attachment()).getSession() == session) {
                current = session;
                break;
            }
            item = item.next;
        }
        close0(current, type);
    }

    private void close(final StoppingType type) {
        T session = null;
        Item<?>[] itemArray = EMPTY;
        markClosed();
        if (owner.loop != null) {
            session = owner;
        } else {
            synchronized (items) {
                Item<T> item = first;
                while (item != null) {
                    if (item.session().loop != null) {
                        session = item.session();
                        break;
                    }
                    item = item.next;
                }
                if (session == null && !items.isEmpty()) {
                    itemArray = items.toArray(new Item<?>[items.size()]);
                }
            }
        }
        if (session != null) {
            session.execute(new Runnable() {

                @Override
                public void run() {
                    throw new UnsupportedOperationException("STUB: not implemented");
                }
            });
        } else {
            for (Item<?> item : itemArray) {
                close0(item.session(), type);
            }
            close0(owner, type);
        }
    }

    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void quickClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void dirtyClose() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static class Item<T extends InternalSession> {

        private final T session;

        private final Object key;

        private final SessionPipeline<T> pipeline;

        Item<T> next;

        Item<T> prev;

        Item(Object key, T session, SessionPipeline<T> pipeline) {
            this.session = session;
            this.key = key;
            this.pipeline = pipeline;
            session.pipelineItem = this;
        }

        T owner() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        Throwable cause() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void cause(Throwable cause) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        T session() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        T next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void markEos() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        boolean canClose() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void unlink() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
