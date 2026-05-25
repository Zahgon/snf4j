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

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.cipher.CipherSuite;
import org.snf4j.tls.crypto.ITranscriptHash;
import org.snf4j.tls.crypto.KeySchedule;
import org.snf4j.tls.extension.NamedGroup;
import org.snf4j.tls.extension.PskKeyExchangeMode;
import org.snf4j.tls.handshake.IHandshake;
import org.snf4j.tls.session.ISession;
import org.snf4j.tls.session.SessionInfo;

public class EngineState implements IEngineState, IEngineProducer {

    private final static ProducedHandshake[] NONE_PRODUCED = new ProducedHandshake[0];

    private final List<ProducedHandshake> produced = new ArrayList<ProducedHandshake>();

    private final List<ProducedHandshake> prepared = new ArrayList<ProducedHandshake>();

    private final Queue<IEngineTask> tasks = new LinkedList<IEngineTask>();

    private final Queue<IEngineTask> runningTasks = new LinkedList<IEngineTask>();

    private final SessionInfo sessionInfo = new SessionInfo();

    private final IEngineParameters parameters;

    private final IEngineHandler handler;

    private final IEngineStateListener listener;

    private MachineState state;

    private int stateBits;

    private ISession session;

    private ITranscriptHash transcriptHash;

    private KeySchedule keySchedule;

    private IHandshake retained;

    private List<KeySharePrivateKey> privateKeys;

    private List<PskContext> psks;

    private int pskModes;

    private CipherSuite cipherSuite;

    private String applicationProtocol;

    private NamedGroup namedGroup;

    private String hostName;

    private int version;

    private boolean producingTasks;

    private int maxFragmentLength = 16384;

    private CertificateCriteria certCryteria;

    private IEarlyDataContext earlyDataContext = NoEarlyDataContext.INSTANCE;

    public EngineState(MachineState state, IEngineParameters parameters, IEngineHandler handler, IEngineStateListener listener) {
        this.state = state;
        this.stateBits = state.bitMask();
        this.parameters = parameters;
        this.handler = handler;
        this.listener = listener;
    }

    @Override
    public IEngineParameters getParameters() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IEngineHandler getHandler() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public IEngineStateListener getListener() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public MachineState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void changeState(MachineState newState) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hadState(MachineState state) {
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
    public boolean isClientMode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isInitialized() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void initialize(KeySchedule keySchedule, CipherSuite cipherSuite) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ITranscriptHash getTranscriptHash() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setTranscriptHash(ITranscriptHash transcriptHash) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISession getSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setSession(ISession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionInfo getSessionInfo() {
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

    public NamedGroup getNamedGroup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setNamedGroup(NamedGroup namedGroup) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getApplicationProtocol() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setApplicationProtocol(String protocol) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getHostName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setHostName(String hostName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setVersion(int version) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public <T extends IHandshake> T getRetainedHandshake() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void retainHandshake(IHandshake handshake) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void produce(ProducedHandshake handshake) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void prepare(ProducedHandshake handshake) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasProduced() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ProducedHandshake[] getProduced() throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean updateTasks() throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasProducingTasks() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasTasks() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasRunningTasks(boolean onlyUndone) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Runnable getTask() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addTask(IEngineTask task) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addPrivateKey(NamedGroup group, PrivateKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public PrivateKey getPrivateKey(NamedGroup group, boolean clearAll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearPrivateKeys() {
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

    public void setEarlyDataContext(IEarlyDataContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addPskContext(PskContext psk) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<PskContext> getPskContexts() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void clearPskContexts() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setPskModes(PskKeyExchangeMode[] modes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasPskMode(PskKeyExchangeMode mode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CertificateCriteria getCertCryteria() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCertCryteria(CertificateCriteria certCryteria) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void cleanup() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
