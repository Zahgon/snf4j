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

import org.snf4j.tls.Args;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.crypto.ITranscriptHash;
import org.snf4j.tls.crypto.KeySchedule;
import org.snf4j.tls.session.ISession;

class EngineStateWrapper implements IEngineState {

    private final IEngineState state;

    private final KeySchedule keySchedule;

    EngineStateWrapper(IEngineState state, KeySchedule keySchedule) {
        Args.checkNull(state, "state");
        this.state = state;
        this.keySchedule = keySchedule;
    }

    @Override
    public IEngineParameters getParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IEngineHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public MachineState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isClientMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isStarted() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isConnected() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ITranscriptHash getTranscriptHash() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public KeySchedule getKeySchedule() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public CipherSuite getCipherSuite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getApplicationProtocol() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getHostName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getMaxFragmentLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IEarlyDataContext getEarlyDataContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
