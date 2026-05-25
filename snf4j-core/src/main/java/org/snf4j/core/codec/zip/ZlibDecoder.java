/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2020-2021 SNF4J contributors
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
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.codec.IDecoder;
import org.snf4j.core.codec.IEventDrivenCodec;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.ISession;

/**
 * Decompresses an array of bytes using the
 * <a href="http://en.wikipedia.org/wiki/Zlib">zlib</a> compression.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class ZlibDecoder extends ZlibCodec implements IDecoder<byte[], ByteBuffer>, IEventDrivenCodec {

    /**
     * The decompressor used by this class to decompress data.
     */
    protected Inflater inflater;

    private boolean finishing;

    private volatile boolean finished;

    private final byte[] dictionary;

    private ByteBuffer in;

    /**
     * Creates a new zlib decoder with the default mode ({@link ZlibCodec.Mode#ZLIB
     * ZLIB}).
     */
    public ZlibDecoder() {
        this(null, Mode.ZLIB);
    }

    /**
     * Creates a new zlib decoder with a preset dictionary for decompression and the
     * default mode ({@link ZlibCodec.Mode#ZLIB ZLIB}).
     *
     * @param dictionary the preset dictionary or {@code null} if no preset
     *                   dictionary should be used
     */
    public ZlibDecoder(byte[] dictionary) {
        this(dictionary, Mode.ZLIB);
    }

    /**
     * Creates a new zlib decoder with the specified mode.
     *
     * @param mode the mode for the zlib decoder
     * @throws NullPointerException if the mode is null
     */
    public ZlibDecoder(Mode mode) {
        this(null, mode);
    }

    /**
     * Creates a new zlib decoder with a preset dictionary for decompression and the
     * mode.
     *
     * @param dictionary the preset dictionary or {@code null} if no preset
     *                   dictionary should be used
     * @param mode       the mode for the zlib decoder
     */
    protected ZlibDecoder(byte[] dictionary, Mode mode) {
        if (mode == null) {
            throw new NullPointerException("mode is null");
        }
        if (mode != Mode.AUTO) {
            boolean nowrap = mode == Mode.RAW;
            inflater = new Inflater(nowrap);
        }
        this.dictionary = dictionary;
    }

    private static boolean nowrap(short cmfflg) {
        /*
		 * CMF: CINFO=0x7 (32K window size)
		 *         CM=0x8 (deflate compression) 
		 * ((CMF << 8) + FLG) is a multiple of 31
		 */
        return (cmfflg & 0xFF00) != 0x7800 || cmfflg % 31 != 0;
    }

    /**
     * Returns upper bound on the decompressed size.
     *
     * @param len the length of the compressed data.
     * @return the upper bound
     */
    protected int inflateBound(int len) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Called right before decompression of a portion of compressed data. It can be
     * used, for example, to parse a customized header. All data being part of the
     * header have to be consumed from the input buffer.
     *
     * @param session the session object the decoder is associated with
     * @param in      the compressed input data
     * @throws Exception if an error has occurred
     */
    protected void preInflate(ISession session, ByteBuffer in) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Called right after decompression of a portion of compressed data. It can be
     * used, for example, to compute the CRC-32 of a decompressed data stream.
     *
     * @param session the session object the decoder is associated with
     * @param data    an array with decompressed data
     * @param off     the start offset in the array at which the data should
     *                be read
     * @param len     the number of bytes to read
     * @throws Exception if an error has occurred
     */
    protected void postInflate(ISession session, byte[] data, int off, int len) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Called right after the decompression has been finished. It can be used, for
     * example, to parse a customized footer. All data being part of the footer have
     * to be consumed from the input buffer.
     *
     * @param session the session object the decoder is associated with
     * @param in      the compressed input data
     * @return {@code true} if all bytes forming the footer has been already
     *         consumed
     * @throws Exception if an error has occurred
     */
    protected boolean postFinish(ISession session, ByteBuffer in) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tells if the decompression has been finished.
     *
     * @return {@code true} if the decompression has been finished.
     */
    public boolean isFinished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decompresses the input data.
     */
    @Override
    public void decode(ISession session, byte[] data, List<ByteBuffer> out) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Does nothing.
     */
    @Override
    public void added(ISession session, ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Finishes the decompression when the associated session is ending
     * ({@link SessionEvent#ENDING}).
     */
    @Override
    public void event(ISession session, SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Does nothing.
     */
    @Override
    public void removed(ISession session, ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
