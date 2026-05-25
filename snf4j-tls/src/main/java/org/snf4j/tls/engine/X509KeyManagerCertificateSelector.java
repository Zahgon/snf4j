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
package org.snf4j.tls.engine;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.X509KeyManager;
import org.snf4j.tls.Args;
import org.snf4j.tls.IntConstant;
import org.snf4j.tls.extension.SignatureScheme;

public class X509KeyManagerCertificateSelector implements ICertificateSelector {

    private final X509KeyManager manager;

    private final String alias;

    public X509KeyManagerCertificateSelector(X509KeyManager manager, String alias) {
        Args.checkNull(manager, "manager");
        this.manager = manager;
        this.alias = alias;
    }

    public X509KeyManagerCertificateSelector(X509KeyManager keyManager) {
        this(keyManager, null);
    }

    SelectedCertificates selectCertificates(CertificateCriteria criteria, String alias) throws CertificateSelectorException, Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SelectedCertificates selectCertificates(CertificateCriteria criteria) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
