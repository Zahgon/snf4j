/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022-2023 SNF4J contributors
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

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.snf4j.core.ByteBufferArray;
import org.snf4j.tls.Args;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.handshake.HandshakeType;

public class ServerNameExtension extends KnownExtension implements IServerNameExtension {

    private final static ExtensionType TYPE = ExtensionType.SERVER_NAME;

    private final static ServerNameExtension EMPTY = new ServerNameExtension();

    private final byte[] hostName;

    private final static AbstractExtensionParser PARSER = new AbstractExtensionParser() {

        @Override
        public ExtensionType getType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public IExtension parse(HandshakeType handshakeType, ByteBufferArray srcs, int remaining) throws Alert {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private byte[] parseName(ByteBufferArray array, int remaining) throws Alert {
            int len = array.getUnsignedShort();
            if (len == 0) {
                throw decodeError("Empty name");
            }
            if (len <= remaining - 2) {
                byte[] data = new byte[len];
                array.get(data);
                return data;
            }
            throw decodeError("Inconsistent name length");
        }
    };

    public ServerNameExtension(String hostName) {
        super(TYPE);
        this.hostName = hostName.getBytes(StandardCharsets.US_ASCII);
        Args.checkRange(this.hostName.length, 1, 0xffff - 5, "hostName length");
    }

    public ServerNameExtension(byte[] hostName) {
        super(TYPE);
        Args.checkRange(hostName.length, 1, 0xffff - 5, "hostName length");
        this.hostName = hostName;
    }

    public ServerNameExtension() {
        super(TYPE);
        this.hostName = null;
    }

    @Override
    public String getHostName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static IExtensionParser getParser() {
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
}
