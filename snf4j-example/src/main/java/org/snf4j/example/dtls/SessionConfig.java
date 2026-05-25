/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020-2021 SNF4J contributors
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
package org.snf4j.example.dtls;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;
import org.snf4j.core.codec.DefaultCodecExecutor;
import org.snf4j.core.codec.ICodecExecutor;
import org.snf4j.core.session.DefaultSessionConfig;
import org.snf4j.core.session.SSLEngineCreateException;

public class SessionConfig extends DefaultSessionConfig {

    final static int MAX_PACKET_SIZE = 1024;

    final static int MAX_APPLICATION_DATA_SIZE = 512;

    static volatile SSLContext sslContext = null;

    SessionConfig() {
        setMinInBufferCapacity(MAX_PACKET_SIZE);
        setMinOutBufferCapacity(MAX_PACKET_SIZE);
        setIgnorePossiblyIncompleteDatagrams(false);
    }

    void load(KeyStore ks, String fileName, char[] password) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    SSLContext getSSLContext() throws SSLEngineCreateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SSLEngine createSSLEngine(boolean clientMode) throws SSLEngineCreateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ICodecExecutor createCodecExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
