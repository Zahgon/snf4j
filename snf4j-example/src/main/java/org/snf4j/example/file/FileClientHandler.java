/*
 * -------------------------------- MIT License --------------------------------
 * 
 * Copyright (c) 2022 SNF4J contributors
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
package org.snf4j.example.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.snf4j.core.EndingAction;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.session.ssl.SSLEngineBuilder;
import org.snf4j.core.timer.ITimerTask;

public class FileClientHandler extends AbstractFileHandler {

    private final Object PROGRESS_UPDATE_EVENT = new Object();

    private final String path;

    ITimerTask progressTimer;

    FileClientHandler(SSLEngineBuilder builder, String path) {
        super(builder);
        config.setEndingAction(EndingAction.STOP).setMinInBufferCapacity(FileClient.BUFFER_SIZE).setMinOutBufferCapacity(FileClient.BUFFER_SIZE);
        this.path = path;
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void timer(Object event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
