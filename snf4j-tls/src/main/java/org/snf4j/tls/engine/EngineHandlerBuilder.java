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

import java.security.SecureRandom;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import org.snf4j.tls.Args;
import org.snf4j.tls.session.ISessionManager;

public class EngineHandlerBuilder {

    private final static TicketInfo[] EMPTY_TICKETS = new TicketInfo[0];

    private final boolean managers;

    private final X509KeyManager km;

    private final String alias;

    private final X509TrustManager tm;

    private final ICertificateSelector selector;

    private final ICertificateValidator validator;

    private ISessionManager manager;

    private SecureRandom random;

    private IEarlyDataHandler earlyDataHandler;

    private TicketInfo[] tickets = new TicketInfo[] { TicketInfo.NO_MAX_EARLY_DATA_SIZE };

    private int padding = 1;

    private IHostNameVerifier hostNameVerifier;

    private IApplicationProtocolHandler protocolHandler;

    private long maxEarlyDataSize;

    public EngineHandlerBuilder(X509KeyManager km, String alias, X509TrustManager tm) {
        Args.checkNull(km, "km");
        Args.checkNull(tm, "tm");
        this.km = km;
        this.alias = alias;
        this.tm = tm;
        selector = null;
        validator = null;
        managers = true;
    }

    public EngineHandlerBuilder(X509KeyManager km, X509TrustManager tm) {
        this(km, null, tm);
    }

    public EngineHandlerBuilder(X509KeyManager km, String alias) {
        Args.checkNull(km, "km");
        this.km = km;
        this.alias = alias;
        this.tm = null;
        selector = null;
        validator = null;
        managers = true;
    }

    public EngineHandlerBuilder(X509KeyManager km) {
        this(km, (String) null);
    }

    public EngineHandlerBuilder(X509TrustManager tm) {
        Args.checkNull(tm, "tm");
        this.km = null;
        this.alias = null;
        this.tm = tm;
        selector = null;
        validator = null;
        managers = true;
    }

    public EngineHandlerBuilder(ICertificateSelector selector, ICertificateValidator validator) {
        Args.checkNull(selector, "selector");
        Args.checkNull(validator, "validator");
        this.selector = selector;
        this.validator = validator;
        km = null;
        alias = null;
        tm = null;
        managers = false;
    }

    public EngineHandlerBuilder(ICertificateSelector selector) {
        Args.checkNull(selector, "selector");
        this.selector = selector;
        validator = null;
        km = null;
        alias = null;
        tm = null;
        managers = false;
    }

    public EngineHandlerBuilder(ICertificateValidator validator) {
        Args.checkNull(validator, "validator");
        selector = null;
        this.validator = validator;
        km = null;
        alias = null;
        tm = null;
        managers = false;
    }

    public EngineHandlerBuilder hostNameVerifier(IHostNameVerifier hostNameVerifier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IHostNameVerifier getHostNameVerifier() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets padding for TLS records so that they are a multiple of the padding
     * value in length on send. A value of 1 turns off padding. Otherwise, the value
     * must be greater than 1.
     *
     * @param padding the padding value
     * @return this builder
     */
    public EngineHandlerBuilder padding(int padding) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getPadding() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandlerBuilder maxEarlyDataSize(long maxSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getMaxEarlyDataSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandlerBuilder ticketInfos(long... maxEarlyDataSizes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public TicketInfo[] getTicketInfos() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandlerBuilder sessionManager(ISessionManager manager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ISessionManager getSessionManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandlerBuilder secureRandom(SecureRandom random) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SecureRandom getSecureRandom() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandlerBuilder earlyDataHandler(IEarlyDataHandler earlyDataHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IEarlyDataHandler getEarlyDataHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandlerBuilder protocolHandler(IApplicationProtocolHandler protocolHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IApplicationProtocolHandler getProtocolHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static TicketInfo[] safeClone(TicketInfo[] tickets) {
        return tickets == null || tickets.length == 0 ? tickets : tickets.clone();
    }

    public EngineHandler build(IEarlyDataHandler earlyDataHandler, IHostNameVerifier hostNameVerifier, IApplicationProtocolHandler protocolHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build(IEarlyDataHandler earlyDataHandler, IHostNameVerifier hostNameVerifier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build(IEarlyDataHandler earlyDataHandler, IApplicationProtocolHandler protocolHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build(IEarlyDataHandler earlyDataHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build(IHostNameVerifier hostNameVerifier, IApplicationProtocolHandler protocolHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build(IHostNameVerifier hostNameVerifier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build(IApplicationProtocolHandler protocolHandler) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EngineHandler build() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
