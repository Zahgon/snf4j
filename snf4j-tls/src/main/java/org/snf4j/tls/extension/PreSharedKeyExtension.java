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
package org.snf4j.tls.extension;

import static org.snf4j.tls.handshake.HandshakeType.SERVER_HELLO;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.Args;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.handshake.HandshakeType;

public class PreSharedKeyExtension extends KnownExtension implements IPreSharedKeyExtension {

    private final static ExtensionType TYPE = ExtensionType.PRE_SHARED_KEY;

    private final static OfferedPsk[] EMPTY = new OfferedPsk[0];

    private final int selectedIdentity;

    private final OfferedPsk[] offeredPsks;

    private final static AbstractExtensionParser PARSER = new AbstractExtensionParser() {

        @Override
        public ExtensionType getType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IExtension parse(HandshakeType handshakeType, ByteBufferArray srcs, int remaining) throws Alert {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    public PreSharedKeyExtension(int selectedIdentity) {
        super(TYPE);
        Args.checkRange(selectedIdentity, 0, 0xffff, "selectedIdentity");
        this.selectedIdentity = selectedIdentity;
        offeredPsks = EMPTY;
    }

    public PreSharedKeyExtension(OfferedPsk... offeredPsks) {
        super(TYPE);
        Args.checkMin(offeredPsks, 1, "offeredPsks");
        this.selectedIdentity = -1;
        this.offeredPsks = offeredPsks;
    }

    @Override
    public OfferedPsk[] getOfferedPsks() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getSelectedIdentity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getDataLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void getData(ByteBuffer buffer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static IExtensionParser getParser() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int bindersLength(OfferedPsk[] offeredPsks) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void updateBinders(byte[] clientHello, int offset, OfferedPsk[] offeredPsks) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
