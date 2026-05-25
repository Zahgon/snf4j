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
package org.snf4j.example.websocket;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.IStreamSession;
import org.snf4j.websocket.AbstractWebSocketHandler;
import org.snf4j.websocket.IWebSocketSession;
import org.snf4j.websocket.IWebSocketSessionConfig;
import org.snf4j.websocket.extensions.compress.PerMessageDeflateExtension;
import org.snf4j.websocket.frame.Frame;
import org.snf4j.websocket.frame.Opcode;
import org.snf4j.websocket.frame.PongFrame;
import org.snf4j.websocket.frame.TextFrame;

public class WebSocketServerHandler extends AbstractWebSocketHandler {

    final private static String USER_ID = "user-id";

    final private static ConcurrentMap<Long, IStreamSession> sessions = new ConcurrentHashMap<Long, IStreamSession>();

    final private String host;

    final private boolean compress;

    private boolean chatMode;

    WebSocketServerHandler(String host, boolean compress) {
        this.host = host;
        this.compress = compress;
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handle(Frame frame) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void send(String msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IWebSocketSessionConfig getConfig() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
