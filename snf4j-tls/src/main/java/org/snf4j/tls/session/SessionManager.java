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

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.engine.IEngineState;
import org.snf4j.tls.extension.IExtension;
import org.snf4j.tls.extension.OfferedPsk;
import org.snf4j.tls.handshake.NewSessionTicket;

public class SessionManager implements ISessionManager {

    private final static Random RANDOM = new Random();

    private final static AtomicLong ID = new AtomicLong();

    private final long id;

    private final long maskAdd;

    private final long idAdd;

    private final long sessionIdAdd;

    private final long nonceAdd;

    private final AtomicLong nextNonce = new AtomicLong();

    private final SessionCache<String> cacheByIpPort;

    private final SessionCache<Long> cacheById;

    private final int lifetime;

    public SessionManager(int lifetime, int limit) {
        id = ID.incrementAndGet();
        this.lifetime = lifetime;
        cacheByIpPort = new SessionCache<String>(limit, lifetime * 1000);
        cacheById = new SessionCache<Long>(limit, lifetime * 1000);
        idAdd = RANDOM.nextLong();
        maskAdd = RANDOM.nextLong();
        sessionIdAdd = RANDOM.nextLong();
        nonceAdd = RANDOM.nextLong();
    }

    public SessionManager() {
        this(86400, 20480);
    }

    static String key(String host, int port) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String key(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void putSession(Session session, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ISession getSession(long sessionId, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISession getSession(long sessionId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ISession getSession(String host, int port, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISession getSession(String host, int port) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Certificate[] prepareCerts(Certificate[] certs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ISession newSession(SessionInfo info, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISession newSession(SessionInfo info) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void removeSession(long sessionId, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void removeSession(long sessionId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void invalidateSession(Session session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Session getSession(byte[] identity, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    UsedSession useSession(OfferedPsk[] psks, CipherSuite cipher, boolean earlyData, String protocol, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public UsedSession useSession(OfferedPsk[] psks, CipherSuite cipher, boolean earlyData, String protocol) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Session checkSession(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putTicket(ISession session, SessionTicket ticket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void removeTicket(ISession session, SessionTicket ticket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    SessionTicket[] getTickets(ISession session, long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SessionTicket[] getTickets(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    NewSessionTicket newTicket(IEngineState state, long maxEarlyDataSize, long currentTime) throws InvalidKeyException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NewSessionTicket newTicket(IEngineState state, long maxEarlyDataSize) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    byte[] ticket(ISession session, long nonce, SecureRandom random) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    byte[] nonce(long nonce) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
