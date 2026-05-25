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

import org.snf4j.core.session.ssl.ClientAuth;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.extension.NamedGroup;
import org.snf4j.tls.extension.PskKeyExchangeMode;
import org.snf4j.tls.extension.SignatureScheme;

public class EngineParametersBuilder {

    private CipherSuite[] cipherSuites = EngineDefaults.getDefaultCipherSuites();

    private NamedGroup[] namedGroups = EngineDefaults.getDefaultNamedGroups();

    private SignatureScheme[] signatureSchemes = EngineDefaults.getDefaulSignatureSchemes();

    private SignatureScheme[] certSignatureSchemes = EngineDefaults.getDefaulCertSignatureSchemes();

    private PskKeyExchangeMode[] pskKeyExchangeModes = EngineDefaults.getDefaultPskKeyExchangeModes();

    private boolean compatibilityMode;

    private int numberOfOfferedSharedKeys = 1;

    private String peerHost;

    private int peerPort = -1;

    private boolean serverNameRequired;

    private DelegatedTaskMode delegatedTaskMode = DelegatedTaskMode.NONE;

    private ClientAuth clientAuth = ClientAuth.NONE;

    private String[] applicationProtocols;

    private boolean skipEndOfEarlyData;

    public EngineParametersBuilder() {
    }

    public EngineParametersBuilder cipherSuites(CipherSuite... cipherSuites) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CipherSuite[] getCipherSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder namedGroups(NamedGroup... namedGroups) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public NamedGroup[] getNamedGroups() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder signatureSchemes(SignatureScheme... signatureSchemes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme[] getSignatureSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder certSignatureSchemes(SignatureScheme... signatureSchemes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SignatureScheme[] getCertSignatureSchemes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder pskKeyExchangeModes(PskKeyExchangeMode... pskKeyExchangeModes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public PskKeyExchangeMode[] getPskKeyExchangeModes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder compatibilityMode(boolean compatibilityMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean getCompatibilityMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder numberOfOfferedSharedKeys(int numberOfOfferedSharedKeys) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getNumberOfOfferedSharedKeys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder peerHost(String peerHost) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getPeerHost() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder peerPort(int peerPort) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getPeerPort() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder serverNameRequired(boolean serverNameRequired) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean getServerNameRequired() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder delegatedTaskMode(DelegatedTaskMode delegatedTaskMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DelegatedTaskMode getDelegatedTaskMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder clientAuth(ClientAuth clientAuth) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ClientAuth getClientAuth() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder applicationProtocols(String... protocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String[] getApplicationProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineParametersBuilder skipEndOfEarlyData(boolean skip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean getSkipEndOfEarlyData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static <T> T[] safeClone(T[] array) {
        return array == null ? null : array.clone();
    }

    public EngineParameters build() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
