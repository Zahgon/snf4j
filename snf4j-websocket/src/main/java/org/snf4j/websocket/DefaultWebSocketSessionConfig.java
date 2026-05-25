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
package org.snf4j.websocket;

import java.net.URI;
import org.snf4j.core.codec.DefaultCodecExecutor;
import org.snf4j.core.codec.ICodecExecutor;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.session.DefaultSessionConfig;
import org.snf4j.websocket.extensions.IExtension;
import org.snf4j.websocket.frame.FrameDecoder;
import org.snf4j.websocket.frame.FrameEncoder;
import org.snf4j.websocket.frame.FrameUtf8Validator;
import org.snf4j.websocket.handshake.HandshakeDecoder;
import org.snf4j.websocket.handshake.HandshakeEncoder;
import org.snf4j.websocket.handshake.HandshakeRequest;
import org.snf4j.websocket.handshake.HandshakeResponse;

/**
 * Default configuration for the Web Socket session.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class DefaultWebSocketSessionConfig extends DefaultSessionConfig implements IWebSocketSessionConfig {

    private final URI requestUri;

    private final boolean clientMode;

    private String requestOrigin;

    private String[] supportedSubProtocols;

    private IExtension[] supportedExtensions;

    private boolean ignoreHostHeaderField;

    private int maxHandshakeFrameLength = 65536;

    private int maxFramePayloadLength = 65536;

    private boolean handleCloseFrame = true;

    /**
     * Constructs the default Web Socket configuration for a client session
     *
     * @param requestUri request URI that will be used to set the Host header field
     *                   and the "Request-URI" of the GET method
     */
    public DefaultWebSocketSessionConfig(URI requestUri) {
        this.requestUri = requestUri;
        clientMode = true;
    }

    /**
     *  Constructs the default Web Socket configuration for a server session
     */
    public DefaultWebSocketSessionConfig() {
        requestUri = null;
        clientMode = false;
    }

    /**
     * Tells if this configuration is for a client or server session.
     *
     * @return {@code true} if this configuration is for a client session.
     */
    public boolean isClientMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public URI getRequestUri() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code null}
     */
    @Override
    public String getRequestOrigin() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the value of the Origin header field in the client Web Socket handshake
     * request.
     *
     * @param requestOrigin the value of the Origin header field, or {@code null} if
     *                      the Origin field is not required in the request.
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setRequestOrigin(String requestOrigin) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code null}
     */
    @Override
    public String[] getSupportedSubProtocols() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the names of supported subprotocols for both the server and client
     * Web Socket sessions.
     *
     * @param subProtocols the names of supported subprotocols
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setSupportedSubProtocols(String... subProtocols) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code null}
     */
    @Override
    public IExtension[] getSupportedExtensions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the supported extensions for both the server and client
     * Web Socket sessions.
     *
     * @param extensions the supported extensions
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setSupportedExtensions(IExtension... extensions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code false}
     */
    @Override
    public boolean ignoreHostHeaderField() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures if the server session associated with this configuration object
     * should ignore missing the Host header field in the received Web Socket
     * handshake request.
     *
     * @param ignoreHostHeaderField {@code true} to ignore missing the Host header
     *                              field
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setIgnoreHostHeaderField(boolean ignoreHostHeaderField) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code 65536}
     */
    @Override
    public int getMaxHandshakeFrameLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the maximum length of the decoded Web Socket handshake frames. All
     * bigger frames will be rejected and the affected handshakes will not be
     * finished and the sessions will be closed.
     *
     * @param maxHandshakeFrameLength the maximum length of the decoded handshake
     *                                frames
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setMaxHandshakeFrameLength(int maxHandshakeFrameLength) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code 65536}
     */
    @Override
    public int getMaxFramePayloadLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the maximum length of the payload in the decoded Web Socket frames. All
     * bigger frames will be rejected and the affected sessions will be closed.
     *
     * @param maxFramePayloadLength the maximum length of the payload in the decoded
     *                              frames
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setMaxFramePayloadLength(int maxFramePayloadLength) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@code true}
     */
    @Override
    public boolean handleCloseFrame() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Configures if the received Web Socket close frames should be handled
     * automatically. The automatically handled close frames will not be passed to
     * the user's web socket handlers.
     *
     * @param handleCloseFrame {@code true} if the close frames should be handled
     *                         automatically
     * @return this session config object
     */
    public DefaultWebSocketSessionConfig setHandleCloseFrame(boolean handleCloseFrame) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ICodecExecutor createCodecExecutor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void switchEncoders(ICodecPipeline pipeline, boolean allowExtensions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void switchDecoders(ICodecPipeline pipeline, boolean allowExtensions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default it does nothing
     */
    @Override
    public void customizeHeaders(HandshakeRequest request) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default it does nothing
     */
    @Override
    public void customizeHeaders(HandshakeResponse response) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     * <p>
     * By default it always returns {@code true}
     */
    @Override
    public boolean acceptRequestUri(URI requestUri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
