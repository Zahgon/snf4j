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
package org.snf4j.core;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.snf4j.core.engine.IEngine;
import org.snf4j.core.factory.DefaultSessionStructureFactory;
import org.snf4j.core.factory.IDatagramHandlerFactory;
import org.snf4j.core.factory.ISessionStructureFactory;
import org.snf4j.core.handler.AbstractDatagramHandler;
import org.snf4j.core.handler.DataEvent;
import org.snf4j.core.handler.IDatagramHandler;
import org.snf4j.core.handler.SessionEvent;
import org.snf4j.core.handler.SessionIncident;
import org.snf4j.core.logger.ExceptionLogger;
import org.snf4j.core.logger.IExceptionLogger;
import org.snf4j.core.logger.ILogger;
import org.snf4j.core.logger.LoggerFactory;
import org.snf4j.core.session.DefaultSessionConfig;
import org.snf4j.core.session.ISessionConfig;
import org.snf4j.core.session.ISessionTimer;
import org.snf4j.core.timer.ITimerTask;

/**
 * Datagram server handler providing functionality to handle multiple remote
 * hosts via a single datagram-orinted session. After receiving the first data
 * from a remote host all communication with this host can be done by a newly
 * created {@link DatagramSession} object that will be associated with the
 * datagram handle created by the {@link IDatagramHandlerFactory} passed in the
 * constructor.
 * <p>
 * <b>Limitations:</b>: As the session objects created by this handler do not
 * implement their own I/O functionalities calling to methods suspending read/write
 * operations will also suspend all other sessions currently handled by this
 * handler.
 *
 * @author <a href="http://snf4j.org">SNF4J.ORG</a>
 */
public class DatagramServerHandler extends AbstractDatagramHandler {

    private final static ILogger LOGGER = LoggerFactory.getLogger(DatagramServerHandler.class);

    private final IExceptionLogger elogger = ExceptionLogger.getInstance();

    /**
     * Sessions currently handled by this handler.
     * <p>
     * <b>Thread-safe considerations:</b> It is not required by the SNF4J
     * framework that the Map implementation is thread safe, however if a class
     * extending this class requires thread-safe access to the map then it
     * should be replaced by a thread-safe implementation. In addition, to
     * provide full thread-safe implementation of this handler the method
     * {@link #getSessions()} should be overridden as well as it is not
     * thread-safe by default.
     */
    protected Map<SocketAddress, DatagramSession> sessions = new HashMap<SocketAddress, DatagramSession>();

    /**
     * A map holding timers used internally by the class. As the map is accessed
     * every time a session is created or closed it may be required by a class
     * extending this class to provide its own implementation for the map. The map
     * implementation can be safely replaced in the constructor.
     * <p>
     * <b>Thread-safe considerations:</b> It is not required by the SNF4J framework
     * that the map implementation is thread safe.
     */
    protected Map<SocketAddress, ITimerTask> timers = new HashMap<SocketAddress, ITimerTask>();

    private final IDatagramHandlerFactory handlerFactory;

    private final ISessionConfig config;

    private final ISessionStructureFactory factory;

    /**
     * Constructs a datagram server handler with the
     * {@link DefaultSessionConfig} and the
     * {@link DefaultSessionStructureFactory}.
     *
     * @param handlerFactory
     *            the factory used to create datagram handlers the will be
     *            associated with newly created sessions for remote hosts
     */
    public DatagramServerHandler(IDatagramHandlerFactory handlerFactory) {
        this(handlerFactory, null, null);
    }

    /**
     * Constructs a datagram server handler with the
     * {@link DefaultSessionStructureFactory}.
     *
     * @param handlerFactory
     *            the factory used to create datagram handlers the will be
     *            associated with newly created sessions for remote hosts
     * @param config
     *            the configuration for a session associated with this handler
     *            or {@code null} to use the default configuration
     */
    public DatagramServerHandler(IDatagramHandlerFactory handlerFactory, ISessionConfig config) {
        this(handlerFactory, config, null);
    }

    /**
     * Constructs a datagram server handler.
     *
     * @param handlerFactory
     *            the factory used to create datagram handlers the will be
     *            associated with newly created sessions for remote hosts
     * @param config
     *            the configuration for a session associated with this handler
     *            or {@code null} to use the default configuration
     * @param factory
     *            the factory used to configure the internal structure of a
     *            session associated with this handler or {@code null}
     *            to use the default structure factory
     */
    public DatagramServerHandler(IDatagramHandlerFactory handlerFactory, ISessionConfig config, ISessionStructureFactory factory) {
        if (handlerFactory == null) {
            throw new NullPointerException();
        }
        this.handlerFactory = handlerFactory;
        this.config = config;
        this.factory = factory;
    }

    @Override
    public void read(byte[] data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(ByteBuffer data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(Object msg) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void handleDecodeException(DatagramSession session, Exception e) {
        if (e instanceof PipelineDecodeException) {
            SessionIncident incident = SessionIncident.DECODING_PIPELINE_FAILURE;
            if (!session.incident(incident, e.getCause())) {
                elogger.error(LOGGER, incident.defaultMessage(), session, e.getCause());
            }
        } else {
            fireException(session, e);
        }
    }

    @Override
    public void read(SocketAddress remoteAddress, byte[] datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(SocketAddress remoteAddress, ByteBuffer datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void read(SocketAddress remoteAddress, Object datagram) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final void cancelTimers() {
        for (ITimerTask timer : timers.values()) {
            timer.cancelTask();
        }
        timers.clear();
    }

    @Override
    public void event(SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void event(SocketAddress remoteAddress, DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns sessions currently handled by this handler. It is expected that
     * the return array can be freely modified by the caller (In other words
     * that no references to it are maintained by the {@link #sessions} object.
     *
     * <p>
     * <b>Thread-safe considerations:</b> By default the access to the
     * {@link #sessions} object done by this method is not thread safe.
     *
     * @return an array of the session
     */
    protected DatagramSession[] getSessions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void exception(Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean incident(SessionIncident incident, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionStructureFactory getFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ISessionConfig getConfig() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireEvent(final InternalSession session, SessionEvent event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireEvent(final InternalSession session, DataEvent event, long length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final void fireException(final InternalSession session, Throwable t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Determines if newly created session should be an engine-driver session. The
     * default implementation always returns {@code null}.
     *
     * @param remoteAddress address of the remote peer for which the session is
     *                      about to be created
     * @param config        the configuration for the session being created
     * @return the engine implementation or {@code null} if the session being
     *         created should not be engine-driven
     * @throws Exception if the engine could not be created
     */
    protected IEngine createEngine(SocketAddress remoteAddress, ISessionConfig config) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    DatagramServerSession createSession(SocketAddress remoteAddress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void timer(Object event) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void setNoReopenTimer(DatagramSession session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void closeSession(DatagramSession session, boolean block) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void closeSession(SocketAddress remoteAddress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void handleClosed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private final static SocketAddress NULL_ADDRESS = new SocketAddress() {

        private static final long serialVersionUID = 7675157540826884055L;
    };
}
