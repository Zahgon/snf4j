/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2023 SNF4J contributors
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
package org.snf4j.tls.session;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SessionCache<K> {

    private final Map<K, CacheEntry<K>> cache;

    private final ReferenceQueue<ISession> queue;

    private int limit;

    private long lifetime;

    public SessionCache(int limit, long lifetime) {
        this.limit = limit;
        this.lifetime = lifetime;
        queue = new ReferenceQueue<ISession>();
        cache = new LinkedHashMap<K, CacheEntry<K>>();
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size(long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void put(K key, ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void put(K key, ISession session, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ISession get(K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ISession get(K key, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void remove(K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    private void refresh() {
        CacheEntry<K> entry;
        while ((entry = (CacheEntry<K>) queue.poll()) != null) {
            K key = entry.getKey();
            if (key == null) {
                continue;
            }
            cache.remove(key, entry);
        }
    }

    private void refreshExpired(long currentTime) {
        refresh();
        if (lifetime == 0) {
            return;
        }
        for (Iterator<CacheEntry<K>> i = cache.values().iterator(); i.hasNext(); ) {
            CacheEntry<K> entry = i.next();
            if (!entry.isValid(currentTime)) {
                i.remove();
            }
        }
    }

    static interface CacheEntry<K> {

        boolean isValid(long currentTime);

        void invalidate();

        K getKey();

        ISession getSession();
    }

    static class SoftCacheEntry<K> extends SoftReference<ISession> implements CacheEntry<K> {

        private K key;

        private long expirationTime;

        public SoftCacheEntry(K key, ISession session, long expirationTime, ReferenceQueue<ISession> queue) {
            super(session, queue);
            this.key = key;
            this.expirationTime = expirationTime;
        }

        @Override
        public boolean isValid(long currentTime) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void invalidate() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K getKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ISession getSession() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
