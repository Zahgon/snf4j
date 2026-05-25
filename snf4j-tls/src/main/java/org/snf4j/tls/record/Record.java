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
package org.snf4j.tls.record;

import java.nio.ByteBuffer;
import org.snf4j.tls.alert.Alert;
import org.snf4j.tls.alert.BadRecordMacAlert;
import org.snf4j.tls.alert.InternalErrorAlert;
import org.snf4j.tls.engine.EngineDefaults;

public class Record {

    private Record() {
    }

    public static final int HEADER_LENGTH = 5;

    public static final int ALERT_CONTENT_LENGTH = 2;

    public static boolean checkForAlert(ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean checkForAlert(ByteBuffer dst, int padding, Encryptor encryptor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int alert(Alert alert, ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int alert(Alert alert, int padding, Encryptor encryptor, ByteBuffer dst) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int header(ContentType type, int contentLength, ByteBuffer dst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int protect(ByteBuffer content, Encryptor encryptor, ByteBuffer dst) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int protect(ByteBuffer[] content, int contentLength, Encryptor encryptor, ByteBuffer dst) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int unprotect(ByteBuffer record, int contentLength, Decryptor decryptor, ByteBuffer dst) throws Alert {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
