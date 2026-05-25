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

import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.snf4j.tls.cipher.CipherSuite;

class Session implements ISession {

    private final static AtomicLong ID = new AtomicLong();

    private final static SessionTicket[] EMPTY = new SessionTicket[0];

    private final long id;

    private final long creationTime;

    private final SessionManager manager;

    private final String host;

    private final int port;

    private final CipherSuite cipherSuite;

    private final Certificate[] peerCertificates;

    private final Certificate[] localCertificates;

    private final Object ticketsLock = new Object();

    private final List<SessionTicket> tickets = new LinkedList<SessionTicket>();

    private final AtomicBoolean valid = new AtomicBoolean(true);

    Session(SessionManager manager, CipherSuite cipherSuite, String host, int port, long creationTime, Certificate[] peerCertificates, Certificate[] localCertificates) {
        this.id = ID.incrementAndGet();
        this.creationTime = creationTime;
        this.manager = manager;
        this.cipherSuite = cipherSuite;
        this.host = host;
        this.port = port;
        this.peerCertificates = peerCertificates;
        this.localCertificates = localCertificates;
    }

    @Override
    public void invalidate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean markInvalid() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getCreationTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getPeerHost() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getPeerPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionManager getManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CipherSuite getCipherSuite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Certificate[] getPeerCertificates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Certificate[] getLocalCertificates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void addTicket(SessionTicket ticket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    SessionTicket[] getTickets(long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void removeTicket(SessionTicket ticket) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Object getTicketsLock() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
