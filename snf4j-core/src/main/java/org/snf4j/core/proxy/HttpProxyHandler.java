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
package org.snf4j.core.proxy;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.session.IStreamSession;
import org.snf4j.core.util.Base64Util;

/**
 * Handles client proxy connections via the HTTP tunneling protocol. For more
 * details about the protocol refer to <a href=
 * "http://en.wikipedia.org/wiki/HTTP_tunnel#HTTP_CONNECT_tunneling">HTTP
 * CONNECT tunneling</a>
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class HttpProxyHandler extends AbstractProxyHandler {

    private final static byte CR = (byte) 13;

    private final static byte LF = (byte) 10;

    private final static String SP_TEXT = " ";

    private final static byte SP = ' ';

    private final static String COLON_TEXT = ":";

    private final static byte COLON = ':';

    private final static byte[] CRLF = new byte[] { CR, LF };

    private final static byte[] HTTP_CONNECT = toBytes("CONNECT");

    private final static String HTTP_VERSION_TEXT = "HTTP/1.1";

    private final static byte[] HTTP_VERSION = toBytes(HTTP_VERSION_TEXT);

    private final static byte[] HOST = toBytes("Host");

    private final static byte[] PROXY_AUTHORIZATION = toBytes("Proxy-Authorization");

    private final static int OK = 200;

    private final List<byte[]> headers = new LinkedList<byte[]>();

    private volatile int minEof = 2;

    private final URI uri;

    private final String username;

    private final String password;

    private Integer statusCode;

    private int headersLength;

    /**
     * Constructs an HTTP tunnel connection handler with the default (10 seconds)
     * connection timeout.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param uri the URI identifying the remote host to which the HTTP tunnel
     *            should be established
     * @throws IllegalArgumentException if the uri is null
     */
    public HttpProxyHandler(URI uri) {
        this(uri, (ISessionConfig) null, null);
    }

    /**
     * Constructs an HTTP tunnel connection handler with the default (10 seconds)
     * connection timeout and user's name/password for the 'Basic' HTTP
     * authentication scheme.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param uri      the URI identifying the remote host to which the HTTP tunnel
     *                 should be established
     * @param username the user's name
     * @param password the user's password
     * @throws IllegalArgumentException if the uri, username or password is null
     */
    public HttpProxyHandler(URI uri, String username, String password) {
        this(uri, username, password, null, null);
    }

    /**
     * Constructs an HTTP tunnel connection handler with the default (10 seconds)
     * connection timeout and configuration.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param uri    the URI identifying the remote host to which the HTTP tunnel
     *               should be established
     * @param config the session configuration object, or {@code null} to use the
     *               default configuration
     * @throws IllegalArgumentException if the uri is null
     */
    public HttpProxyHandler(URI uri, ISessionConfig config) {
        this(uri, config, null);
    }

    /**
     * Constructs an HTTP tunnel connection handler with the default (10 seconds)
     * connection timeout, user's name/password for the 'Basic' HTTP authentication
     * scheme and configuration.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param uri      the URI identifying the remote host to which the HTTP tunnel
     *                 should be established
     * @param username the user's name
     * @param password the user's password
     * @param config   the session configuration object, or {@code null} to use the
     *                 default configuration
     * @throws IllegalArgumentException if the uri, username or password is null
     */
    public HttpProxyHandler(URI uri, String username, String password, ISessionConfig config) {
        this(uri, username, password, config, null);
    }

    /**
     * Constructs an HTTP tunnel connection handler with the default (10 seconds)
     * connection timeout, configuration and factory.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param uri     the URI identifying the remote host to which the HTTP tunnel
     *                should be established
     * @param config  the session configuration object, or {@code null} to use the
     *                default configuration
     * @param factory the factory that will be used to configure the internal
     *                structure of the associated session, or {@code null} to use
     *                the default factory
     * @throws IllegalArgumentException if the uri is null
     */
    public HttpProxyHandler(URI uri, ISessionConfig config, ISessionStructureFactory factory) {
        super(config, factory);
        checkNull(uri, "uri");
        this.uri = uri;
        username = null;
        password = null;
    }

    /**
     * Constructs an HTTP tunnel connection handler with the default (10 seconds)
     * connection timeout, user's name/password for the 'Basic' HTTP authentication
     * scheme, configuration and factory.
     * <p>
     * NOTE: The connection timeout will have no effect if the associated session
     * does not support a session timer.
     *
     * @param uri      the URI identifying the remote host to which the HTTP tunnel
     *                 should be established
     * @param username the user's name
     * @param password the user's password
     * @param config   the session configuration object, or {@code null} to use the
     *                 default configuration
     * @param factory  the factory that will be used to configure the internal
     *                 structure of the associated session, or {@code null} to use
     *                 the default factory
     * @throws IllegalArgumentException if the uri, username or password is null
     */
    public HttpProxyHandler(URI uri, String username, String password, ISessionConfig config, ISessionStructureFactory factory) {
        super(config, factory);
        checkNull(uri, "uri");
        checkNull(username, "username");
        checkNull(password, "password");
        this.uri = uri;
        if (username.indexOf(COLON_TEXT) >= 0) {
            throw new IllegalArgumentException("username contains a colon");
        }
        this.username = username;
        this.password = password;
    }

    private static final byte[] toBytes(String s) {
        return s.getBytes(StandardCharsets.US_ASCII);
    }

    private int eol(byte[] data, int off, int end) {
        switch(end - off) {
            case 0:
                return 0;
            case 1:
                return data[off] == LF ? 1 : 0;
            default:
                byte b = data[off];
                if (b == CR) {
                    return data[off + 1] == LF ? 2 : 0;
                }
                return b == LF ? 1 : 0;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if the connection timeout is negative
     */
    @Override
    public HttpProxyHandler connectionTimeout(long connectionTimeout) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures the handling of line terminators in responses from a HTTP proxy
     * server
     *
     * @param allow {@code true} to allow both CRLF and LF line terminators in
     *              responses from a HTTP proxy server, or otherwise (default
     *              option) only CRLF will be allowed.
     * @return this handler
     */
    public HttpProxyHandler allowBothTerminators(boolean allow) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int available(byte[] data, int off, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private int findEol(byte[] data, int off) {
        int end = data.length;
        for (int i = off; i < end; ++i) {
            int eol = eol(data, i, end);
            if (eol >= minEof) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void read(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Appends an HTTP header to the HTTP CONNECT method request being sent to an
     * HTTP proxy server.
     * <p>
     * There is no need to append the Host header as it is appended by default.
     *
     * @param name  the name of the HTTP header
     * @param value the value of the HTTP header
     */
    public void appendHeader(String name, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the default port number associated with the scheme in the provided
     * URI.
     * <p>
     * By default it calls {@code uri.toURL().getDefaultPort()}
     *
     * @param uri the provided URI
     * @return the default port number, or {@code -1} if the default port number is
     *         not defined for the scheme in the provided URI
     * @throws Exception if the provided URI was malformed
     */
    protected int getDefaultPort(URI uri) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void append(ByteBuffer frame, byte[] name, byte[] value) {
        frame.put(name);
        frame.put(COLON);
        frame.put(SP);
        frame.put(value);
        frame.put(CRLF);
    }

    @Override
    protected void handleReady() throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
