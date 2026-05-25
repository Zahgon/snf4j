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
package org.snf4j.core.session.ssl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

class ProtocolDefaults {

    final static String TLS = "TLS";

    final static String DTLS = "DTLS";

    static volatile ProtocolDefaults tlsDefaults;

    static volatile ProtocolDefaults dtlsDefaults;

    final static String[] PROTOCOLS = new String[] { "TLSv1.3", "TLSv1.2", "TLSv1.1", "TLSv1", "DTLSv1.2", "DTLSv1.0" };

    final static String[] CIPHERS = new String[] { "TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256" };

    private final Set<String> supportedCiphers;

    private final String[] defaultCiphers;

    private final Set<String> supportedProtocols;

    private final String[] defaultProtocols;

    ProtocolDefaults(String protocol) {
        SSLEngine engine = ProtocolDefaults.defaultEngine(protocol);
        supportedCiphers = ProtocolDefaults.supportedCiphers(engine);
        defaultCiphers = ProtocolDefaults.defaultCiphers(engine, supportedCiphers);
        supportedProtocols = ProtocolDefaults.supportedProtocols(engine);
        defaultProtocols = ProtocolDefaults.defaultProtocols(engine, supportedProtocols);
    }

    Set<String> supportedCiphers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String[] defaultCiphers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Set<String> supportedProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String[] defaultProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ProtocolDefaults instance(SSLEngine engine) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static ProtocolDefaults instance(boolean dtls) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean isDtls(SSLEngine engine) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static SSLEngine defaultEngine(String protocol) throws Error {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Set<String> supportedCiphers(SSLEngine engine) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String[] defaultCiphers(SSLEngine engine, Set<String> supportedCiphers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static Set<String> supportedProtocols(SSLEngine engine) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static String[] defaultProtocols(SSLEngine engine, Set<String> supportedPtotocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
