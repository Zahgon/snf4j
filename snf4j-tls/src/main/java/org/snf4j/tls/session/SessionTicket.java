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

import org.snf4j.tls.cipher.CipherSuite;

public class SessionTicket {

    private final CipherSuite cipherSuite;

    private final String protocol;

    private final byte[] psk;

    private final byte[] ticket;

    private final long creationTime;

    private final long expirationTime;

    private final long ageAdd;

    private final long maxEarlyDataSize;

    public SessionTicket(CipherSuite cipherSuite, String protocol, byte[] psk, byte[] ticket, long lifetime, long ageAdd, long maxEarlyDataSize, long creationTime) {
        this.cipherSuite = cipherSuite;
        this.protocol = protocol;
        this.psk = psk;
        this.ticket = ticket;
        this.creationTime = creationTime;
        this.ageAdd = ageAdd;
        expirationTime = creationTime + lifetime * 1000;
        this.maxEarlyDataSize = maxEarlyDataSize;
    }

    public SessionTicket(CipherSuite cipherSuite, String protocol, byte[] psk, byte[] ticket, long lifetime, long ageAdd, long maxEarlyDataSize) {
        this(cipherSuite, protocol, psk, ticket, lifetime, ageAdd, maxEarlyDataSize, System.currentTimeMillis());
    }

    public CipherSuite getCipherSuite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getProtocol() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] getPsk() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public byte[] getTicket() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getCreationTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getAgeAdd() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isValid(long currentTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isValid() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean forEarlyData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if this ticket supports the early data and specified protocol name
     * (ALPN).
     *
     * @param protocol the name of protocol or {@code null} if the protocol name is
     *                 not used
     * @return {@code true} if this ticket supports the early data and specified
     *         protocol name
     */
    public boolean forEarlyData(String protocol) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the maximum size of the early data that can be sent when using this
     * ticket. For tickets not supporting the early data it should return value less
     * than {@code 1}.
     *
     * @return the maximum early data size
     */
    public long getMaxEarlyDataSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
