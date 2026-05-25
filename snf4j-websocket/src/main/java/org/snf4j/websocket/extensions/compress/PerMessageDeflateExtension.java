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
package org.snf4j.websocket.extensions.compress;

import java.util.ArrayList;
import java.util.List;
import org.snf4j.core.codec.ICodecPipeline;
import org.snf4j.core.codec.IDecoder;
import org.snf4j.core.codec.IEncoder;
import org.snf4j.websocket.IWebSocketSessionConfig;
import org.snf4j.websocket.extensions.GroupIdentifier;
import org.snf4j.websocket.extensions.IExtension;
import org.snf4j.websocket.extensions.InvalidExtensionException;
import org.snf4j.websocket.frame.Frame;

/**
 * The WebSocket Per-Message Compression Extension as described in RFC 7692
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class PerMessageDeflateExtension implements IExtension {

    final static String NAME = "permessage-deflate";

    /**
     * The default key identifying the pre-message deflate decoder
     * {@link PerMessageDeflateDecoder} in the default codec pipeline created by the
     * SNF4J framework.
     */
    public final static String PERMESSAGE_DEFLATE_DECODER = "permessage-deflate-decoder";

    /**
     * The default key identifying the pre-message deflate encoder
     * {@link PerMessageDeflateEncoder} in the default codec pipeline created by the
     * SNF4J framework.
     */
    public final static String PERMESSAGE_DEFLATE_ENCODER = "permessage-deflate-encoder";

    /**
     * The context takeover control options
     */
    public enum NoContext {

        /**
         * The no context takeover is forbidden
         */
        FORBIDDEN,
        /**
         * The context takeover is the preferred option but can be changed during the negotiation
         */
        OPTIONAL,
        /**
         * The no context takeover is required
         */
        REQUIRED
    }

    private final int compressionLevel;

    private final NoContext compressNoContext;

    private final NoContext decompressNoContext;

    private final boolean clientMode;

    private final int minInflateBound;

    private final PerMessageDeflateParams params;

    /**
     * Constructs a pre-message deflate extension with specified compression level,
     * minimum upper bound on the decompressed size and the context takeover control
     * options for compression/decompression.
     *
     * @param compressionLevel    the compression level (0-9)
     * @param minInflateBound     determines the minimum upper bound on the
     *                            decompressed size. Setting this parameter to
     *                            proper value may speed up the decompression of
     *                            highly compressed data.
     * @param compressNoContext   the context takeover control for compression
     * @param decompressNoContext the context takeover control for decompression
     */
    public PerMessageDeflateExtension(int compressionLevel, int minInflateBound, NoContext compressNoContext, NoContext decompressNoContext) {
        if (compressionLevel < 0 || compressionLevel > 9) {
            throw new IllegalArgumentException("Invalid compressionLevel: " + compressionLevel + " (expected: 0-9)");
        }
        this.compressionLevel = compressionLevel;
        this.compressNoContext = compressNoContext;
        this.decompressNoContext = decompressNoContext;
        this.minInflateBound = minInflateBound;
        clientMode = false;
        params = null;
    }

    /**
     * Constructs a pre-message deflate extension with specified compression level
     * and the context takeover control options for compression/decompression.
     *
     * @param compressionLevel    the compression level (0-9)
     * @param compressNoContext   the context takeover control for compression
     * @param decompressNoContext the context takeover control for decompression
     */
    public PerMessageDeflateExtension(int compressionLevel, NoContext compressNoContext, NoContext decompressNoContext) {
        this(compressionLevel, 0, compressNoContext, decompressNoContext);
    }

    /**
     * Constructs a pre-message deflate extension with specified compression level
     * and the {@link NoContext#OPTIONAL OPTIONAL} context takeover control option for
     * compression/decompression.
     *
     * @param compressionLevel the compression level (0-9)
     */
    public PerMessageDeflateExtension(int compressionLevel) {
        this(compressionLevel, NoContext.OPTIONAL, NoContext.OPTIONAL);
    }

    /**
     * Constructs a pre-message deflate extension with default compression level (6)
     * and the {@link NoContext#OPTIONAL OPTIONAL} context takeover control option for
     * compression/decompression.
     */
    public PerMessageDeflateExtension() {
        this(6, NoContext.OPTIONAL, NoContext.OPTIONAL);
    }

    PerMessageDeflateExtension(int compressionLevel, int minInflateBound, PerMessageDeflateParams params, boolean clientMode) {
        this.compressionLevel = compressionLevel;
        this.minInflateBound = minInflateBound;
        this.params = params;
        this.clientMode = clientMode;
        compressNoContext = null;
        decompressNoContext = null;
    }

    /**
     * {@inheritDoc}
     *
     * @return "permessage-deflate"
     */
    @Override
    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     *
     * @return {@link GroupIdentifier#COMPRESSION}
     */
    @Override
    public Object getGroupId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean wrongName(List<String> extension) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IExtension acceptOffer(List<String> offer) throws InvalidExtensionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public IExtension validateResponse(List<String> response) throws InvalidExtensionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<String> offer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<String> response() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updatePipeline(ICodecPipeline pipeline, IEncoder<Frame, Frame> encoder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void updateEncoders(ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updatePipeline(ICodecPipeline pipeline, IDecoder<Frame, Frame> decoder) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void updateDecoders(ICodecPipeline pipeline) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
