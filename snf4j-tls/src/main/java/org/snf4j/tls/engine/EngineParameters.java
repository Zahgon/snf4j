/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022-2024 SNF4J contributors
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

import org.snf4j.core.session.ssl.ClientAuth;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.extension.NamedGroup;
import org.snf4j.tls.extension.PskKeyExchangeMode;
import org.snf4j.tls.extension.SignatureScheme;

public class EngineParameters implements IEngineParameters {

    private final static String[] EMPTY = new String[0];

    private final CipherSuite[] cipherSuites;

    private final NamedGroup[] namedGroups;

    private final SignatureScheme[] signatureSchemes;

    private final SignatureScheme[] certSignatureSchemes;

    private final PskKeyExchangeMode[] pskKeyExchangeModes;

    private final boolean compatibilityMode;

    // = 1;
    private final int numberOfOfferedSharedKeys;

    private final String peerHost;

    private final int peerPort;

    private final boolean serverNameRequired;

    // = DelegatedTaskMode.NONE;
    private final DelegatedTaskMode delegatedTaskMode;

    private final ClientAuth clientAuth;

    private final String[] applicationProtocols;

    private final boolean skipEndOfEarlyData;

    public EngineParameters(CipherSuite[] cipherSuites, NamedGroup[] namedGroups, SignatureScheme[] signatureSchemes, SignatureScheme[] certSignatureSchemes, PskKeyExchangeMode[] pskKeyExchangeModes, boolean compatibilityMode, int numberOfOfferedSharedKeys, String peerHost, int peerPort, boolean serverNameRequired, DelegatedTaskMode delegatedTaskMode, ClientAuth clientAuth, String[] applicationProtocols, boolean skipEndOfEarlyData) {
        super();
        this.cipherSuites = cipherSuites;
        this.namedGroups = namedGroups;
        this.signatureSchemes = signatureSchemes;
        this.certSignatureSchemes = certSignatureSchemes;
        this.pskKeyExchangeModes = pskKeyExchangeModes;
        this.compatibilityMode = compatibilityMode;
        this.numberOfOfferedSharedKeys = numberOfOfferedSharedKeys;
        this.peerHost = peerHost;
        this.peerPort = peerPort;
        this.serverNameRequired = serverNameRequired;
        this.delegatedTaskMode = delegatedTaskMode;
        this.clientAuth = clientAuth;
        this.applicationProtocols = applicationProtocols == null ? EMPTY : applicationProtocols;
        this.skipEndOfEarlyData = skipEndOfEarlyData;
    }

    @Override
    public CipherSuite[] getCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NamedGroup[] getNamedGroups() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SignatureScheme[] getSignatureSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SignatureScheme[] getCertSignatureSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public PskKeyExchangeMode[] getPskKeyExchangeModes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isCompatibilityMode() {
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
    public boolean isServerNameRequired() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getNumberOfOfferedSharedKeys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public DelegatedTaskMode getDelegatedTaskMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ClientAuth getClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getApplicationProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean skipEndOfEarlyData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
