/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2023-2024 SNF4J contributors
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

import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateRevokedException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.snf4j.tls.Args;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.BadCertificateAlert;
import org.snf4j.tls.alert.CertificateExpiredAlert;
import org.snf4j.tls.alert.CertificateRevokedAlert;
import org.snf4j.tls.alert.UnsupportedCertificateAlert;

public class X509TrustManagerCertificateValidator implements ICertificateValidator {

    private final static String UNKNOWN = "UNKNOWN";

    private final X509TrustManager manager;

    private final boolean ignoreAlgorithms;

    public X509TrustManagerCertificateValidator(X509TrustManager manager) {
        this(manager, false);
    }

    public X509TrustManagerCertificateValidator(X509TrustManager manager, boolean ignoreAlgorithms) {
        Args.checkNull(manager, "manager");
        this.manager = manager;
        this.ignoreAlgorithms = ignoreAlgorithms;
    }

    @Override
    public Alert validateCertificates(CertificateValidateCriteria criteria, X509Certificate[] certs) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Alert validateRawKey(CertificateValidateCriteria criteria, PublicKey key) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
