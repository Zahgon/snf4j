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
package org.snf4j.websocket.handshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.snf4j.core.SSLSession;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.session.ISession;
import org.snf4j.websocket.IWebSocketSessionConfig;
import org.snf4j.websocket.extensions.IExtension;
import org.snf4j.websocket.extensions.InvalidExtensionException;

/**
 * Default handshaker responsible for processing of the Web Socket handshake phase.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class Handshaker implements IHandshaker {

    private final static IExtension[] EMPTY = new IExtension[0];

    private final boolean clientMode;

    private final IWebSocketSessionConfig config;

    private volatile boolean finished;

    private boolean closing;

    private String key;

    private volatile String subProtocol;

    private volatile URI uri;

    private ISession session;

    private String cause;

    private volatile List<IExtension> extensions;

    /**
     * Constructs the handshaker.
     *
     * @param config     the Web Socket configuration of the session this
     *                   handshaker should be associated with
     * @param clientMode the mode in witch this handshaker should operate
     */
    public Handshaker(IWebSocketSessionConfig config, boolean clientMode) {
        this.config = config;
        this.clientMode = clientMode;
    }

    @Override
    public void setSession(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isClientMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isFinished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isClosing() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getSubProtocol() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasExtensions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IExtension getExtension(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IExtension[] getExtensions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String[] getExtensionNames() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void updateExtensionEncoders(ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void updateExtensionDecoders(ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public URI getUri() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getClosingReason() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void cause(String cause) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    HandshakeRequest request() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void acceptVersion(HandshakeRequest request) throws HandshakeAcceptException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void acceptBasicFields(HandshakeRequest request) throws HandshakeAcceptException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    byte[] acceptKey(HandshakeRequest request) throws HandshakeAcceptException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void acceptSubProtocol(HandshakeRequest request) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean addExtension(IExtension extension) {
        if (extensions == null) {
            extensions = new ArrayList<IExtension>();
            extensions.add(extension);
        } else if (getExtension(extension.getName()) == null) {
            extensions.add(extension);
        } else {
            return false;
        }
        return true;
    }

    void acceptExtensions(HandshakeRequest request) throws HandshakeAcceptException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void acceptUri(HandshakeRequest request) throws HandshakeAcceptException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    HandshakeResponse accept(HandshakeRequest request) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static boolean contains(String tokens, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean validateBasicFields(HandshakeFrame frame) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean validateKeyChallenge(HandshakeResponse response) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean validateSubProtocol(HandshakeResponse response) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean validateExtensions(HandshakeResponse response) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean validate(HandshakeResponse response) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public HandshakeFrame handshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public HandshakeFrame handshake(HandshakeFrame frame) throws InvalidHandshakeException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
