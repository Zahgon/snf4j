/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020 SNF4J contributors
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
package org.snf4j.core.codec.zip;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.snf4j.core.session.ISession;

/**
 * Decompresses an array of bytes using the
 * <a href="http://en.wikipedia.org/wiki/Gzip">gzip</a> compression as
 * specified in <a href="http://tools.ietf.org/html/rfc1952">RFC 1952</a>.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class GzipDecoder extends ZlibDecoder {

    private static final int FHCRC = 0x02;

    private static final int FEXTRA = 0x04;

    private static final int FNAME = 0x08;

    private static final int FCOMMENT = 0x10;

    private static final int RESERVED = 0xE0;

    private enum State {

        HEADER_INIT,
        HEADER,
        XLEN_INIT,
        XLEN,
        XLEN_SKIP,
        FNAME_INIT,
        FNAME,
        FCOMMENT_INIT,
        FCOMMENT,
        FHCRC_INIT,
        FHCRC,
        HEADER_END
    }

    private final CRC32 crc = new CRC32();

    private State state = State.HEADER_INIT;

    private int flg;

    private ByteBuffer buffer;

    private boolean header;

    /**
     * Creates a new gzip decoder.
     */
    public GzipDecoder() {
        super(Mode.RAW);
    }

    private static boolean copy(ByteBuffer src, ByteBuffer dst) {
        int len = Math.min(src.remaining(), dst.remaining());
        src.get(dst.array(), dst.position(), len);
        dst.position(dst.position() + len);
        if (dst.hasRemaining()) {
            return false;
        }
        dst.flip();
        return true;
    }

    private boolean skip(ByteBuffer in) {
        byte b;
        while (in.hasRemaining()) {
            b = in.get();
            crc.update(b);
            if (b == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCrc(ByteBuffer in, int size) {
        long crcValue = 0;
        long mask = 0;
        for (int i = 0; i < size; ++i) {
            crcValue |= ((long) in.get() & 0xff) << i * 8;
            mask |= 0xff << i * 8;
        }
        return crcValue == (crc.getValue() & mask);
    }

    /**
     * Parses the gzip header.
     */
    @Override
    protected void preInflate(ISession session, ByteBuffer in) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Updates the CRC-32.
     */
    @Override
    protected void postInflate(ISession session, byte[] data, int off, int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses the gzip footer.
     */
    @Override
    protected boolean postFinish(ISession session, ByteBuffer in) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
