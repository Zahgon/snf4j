/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2019 SNF4J contributors
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
package org.snf4j.core.codec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.snf4j.core.session.ISession;

abstract class CompoundCodec<C extends ICodec<?, ?>, I, O> implements ICodec<I, O> {

    private final List<C> codecs = new LinkedList<C>();

    private final int last;

    CompoundCodec(@SuppressWarnings("unchecked") C... codecs) {
        int last = -1;
        if (getOutboundType() == Void.class) {
            throw new IllegalStateException("compound " + type() + " with void output");
        }
        if (codecs.length > 0) {
            Class<?> inType = getInboundType();
            Class<?> outType = null;
            for (int i = 0; i < codecs.length; ++i) {
                C codec = codecs[i];
                if (codec.getInboundType().isAssignableFrom(inType)) {
                    this.codecs.add(codec);
                    if (codec.getOutboundType() != Void.class) {
                        outType = inType = codec.getOutboundType();
                        last = i;
                    }
                } else {
                    throw new IllegalArgumentException("incompatible " + type() + "(s)");
                }
            }
            if (outType != null) {
                if (!getOutboundType().isAssignableFrom(outType)) {
                    throw new IllegalArgumentException("last " + type() + " has incompatible outbound type");
                }
            } else {
                throw new IllegalArgumentException("no " + type() + " produces output");
            }
        }
        this.last = last;
    }

    abstract void process(ICodec<?, ?> codec, ISession session, Object data, List<Object> out) throws Exception;

    abstract String type();

    @SuppressWarnings("unchecked")
    final void process(ISession session, I data, List<O> out) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
