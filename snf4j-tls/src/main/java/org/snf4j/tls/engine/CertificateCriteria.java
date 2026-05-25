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

import java.security.cert.X509Certificate;
import org.snf4j.tls.Args;
import org.snf4j.tls.IntConstant;
import org.snf4j.tls.extension.SignatureScheme;
import org.snf4j.tls.handshake.CertificateType;

public class CertificateCriteria {

    private final boolean server;

    private final CertificateType type;

    private final String hostName;

    private final SignatureScheme[] schemes;

    private final SignatureScheme[] certSchemes;

    private final SignatureScheme[] localSchemes;

    public CertificateCriteria(boolean server, CertificateType type, String hostName, SignatureScheme[] schemes, SignatureScheme[] certSchemes, SignatureScheme[] localSchemes) {
        Args.checkNull(type, "type");
        Args.checkNull(schemes, "schemes");
        Args.checkNull(localSchemes, "localSchemes");
        this.server = server;
        this.type = type;
        this.hostName = hostName;
        this.schemes = schemes;
        this.certSchemes = certSchemes;
        this.localSchemes = localSchemes;
    }

    public boolean isServer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CertificateType getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getHostName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme[] getSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme[] getCertSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme[] getLocalSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static SignatureScheme match(X509Certificate cert, SignatureScheme[] schemes, SignatureScheme[] secondarySchemes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static SignatureScheme matchByKey(X509Certificate cert, SignatureScheme[] schemes, SignatureScheme[] secondarySchemes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean allMatch(X509Certificate[] certs, int offset, int length, SignatureScheme[] schemes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme match(X509Certificate cert) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme matchByKey(X509Certificate cert) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean allMatch(X509Certificate[] certs, int offset, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean allMatch(X509Certificate[] certs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
