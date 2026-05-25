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

import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.crypto.TrafficKeys;
import org.snf4j.tls.handshake.IHandshake;
import org.snf4j.tls.handshake.KeyUpdateRequest;
import org.snf4j.tls.record.Cryptor;
import org.snf4j.tls.record.Decryptor;
import org.snf4j.tls.record.Encryptor;
import org.snf4j.tls.record.IDecryptorHolder;
import org.snf4j.tls.record.IEncryptorHolder;
import org.snf4j.tls.record.RecordType;

public class EngineStateListener implements IEngineStateListener, IEncryptorHolder, IDecryptorHolder {

    private final Encryptor[] encryptors = new Encryptor[RecordType.values().length];

    private final Decryptor[] decryptors = new Decryptor[RecordType.values().length];

    private int decryptor;

    private int encryptor;

    @Override
    public Decryptor getDecryptor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Encryptor getEncryptor(RecordType type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Encryptor getEncryptor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private long keyLimit(IEngineState state, TrafficKeys keys) {
        return state.getHandler().getKeyLimit(state.getCipherSuite(), keys.getAead().getKeyLimit());
    }

    @Override
    public void onNewTrafficSecrets(IEngineState state, RecordType recordType) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onNewReceivingTraficKey(IEngineState state, RecordType recordType) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onNewSendingTraficKey(IEngineState state, RecordType recordType) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onKeyUpdate(IEngineState state, KeyUpdateRequest request) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onHandshake(IEngineState state, IHandshake handshake) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onHandshakeCreate(IEngineState state, IHandshake handshake, boolean isHRR) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void onCleanup(IEngineState state) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void produceChangeCipherSpec(IEngineProducer producer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void prepareChangeCipherSpec(IEngineProducer producer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
