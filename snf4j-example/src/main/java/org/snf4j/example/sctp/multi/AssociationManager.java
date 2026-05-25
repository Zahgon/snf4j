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
package org.snf4j.example.sctp.multi;

import java.net.SocketAddress;
import java.util.Set;
import org.snf4j.core.handler.ISctpHandler;
import org.snf4j.core.session.ISctpMultiSession;
import com.sun.nio.sctp.Association;

class AssociationManager {

    final AssociationContext[] contexts;

    final ISctpHandler handler;

    AssociationManager(ISctpHandler handler, int maxCount, SocketAddress... peers) {
        this.handler = handler;
        contexts = new AssociationContext[peers.length];
        for (int i = 0; i < peers.length; ++i) {
            contexts[i] = new AssociationContext(peers[i], maxCount);
        }
    }

    ISctpMultiSession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    AssociationContext getContext(Association association) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    AssociationContext getContext(SocketAddress peer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
