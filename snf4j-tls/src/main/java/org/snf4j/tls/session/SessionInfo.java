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
import org.snf4j.tls.cipher.CipherSuite;

public class SessionInfo {

    private String peerHost;

    private int peerPort = -1;

    private CipherSuite cipher;

    private Certificate[] peerCerts;

    private Certificate[] localCerts;

    public String peerHost() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionInfo peerHost(String peerHost) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int peerPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionInfo peerPort(int peerPort) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CipherSuite cipher() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionInfo cipher(CipherSuite cipher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Certificate[] peerCerts() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionInfo peerCerts(Certificate[] certs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Certificate[] localCerts() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionInfo localCerts(Certificate[] certs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
