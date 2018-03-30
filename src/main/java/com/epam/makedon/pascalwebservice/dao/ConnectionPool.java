package com.epam.makedon.pascalwebservice.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool implements Serializable, Cloneable {
    private static final Logger LOGGER;
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated;
    private static ReentrantLock lock;

    static {
        LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
        instanceCreated = new AtomicBoolean(false);
        lock = new ReentrantLock();
    }

    private enum Type {
        DATABASE_PROPERTY(File.separator + "property" + File.separator + "database.properties"),
        URL("url"),
        POOL_SIZE("poolSize");

        private String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private BlockingQueue<ProxyConnection> connectionQueue;
    private int poolSize;

    private ConnectionPool() {
        if (instanceCreated.get()) {
            LOGGER.error("Tried to clone connection pool with reflection api");
            throw new DaoRuntimeException("Tried to clone connection pool with reflection api");
        }

        registerJDBCDriver();
        initPool();
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (!instanceCreated.get()) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws DaoException {
        ProxyConnection proxyConnection;
        try {
            proxyConnection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new DaoException("get connection", e);
        }
        return proxyConnection;
    }

    public void destroy() throws DaoException {
        closeConnections();
        destroyDrivers();
    }

    void releaseConnection(ProxyConnection proxyConnection) {
        try {
            connectionQueue.put(proxyConnection);
        } catch (InterruptedException e) {
            LOGGER.error("release connection", e);
            throw new DaoRuntimeException("release connection", e);
        }
    }

    private void closeConnections() throws DaoException {
        for (int index = 0; index < poolSize; index++) {
            ProxyConnection proxyConnection = null;
            try {
                proxyConnection = connectionQueue.take();
            } catch (InterruptedException e) {
                throw new DaoException("", e);
            } finally {
                if (proxyConnection != null) {
                    try {
                        proxyConnection.closeConnection();
                    } catch (SQLException e) {
                        throw new DaoException("", e);
                    }
                }
            }
        }
    }

    private void destroyDrivers() throws DaoException {
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            throw new DaoException("", e);
        }
    }

    private void registerJDBCDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.error("Mysql jdbc driver hasn't loaded", e);
            throw new DaoRuntimeException("Mysql jdbc driver hasn't loaded", e);
        }
    }

    private void initPool() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(Type.DATABASE_PROPERTY.toString());

        if (url == null) {
            LOGGER.error("database.properties hasn't found");
            throw new DaoRuntimeException("database.properties hasn't found");
        }

        Properties databaseProps = new Properties();
        try {
            databaseProps.load(new FileInputStream(new File(url.toURI())));
        } catch (URISyntaxException | IOException e) {
            LOGGER.error("", e);
            throw new DaoRuntimeException("", e);
        }

        String urlDatabase = databaseProps.getProperty(Type.URL.toString());
        poolSize = Integer.parseInt(databaseProps.getProperty(Type.POOL_SIZE.toString()));

        databaseProps.remove(Type.URL.toString());
        databaseProps.remove(Type.POOL_SIZE.toString());

        connectionQueue = new ArrayBlockingQueue<ProxyConnection>(poolSize);

        for (int index = 0; index < poolSize; index++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(urlDatabase, databaseProps);
            } catch (SQLException e) {
                LOGGER.error("Hasn't found connection with database");
                throw new DaoRuntimeException("Hasn't found connection with database");
            }

            ProxyConnection proxyConnection = new ProxyConnection(connection);
            try {
                connectionQueue.put(proxyConnection);
            } catch (InterruptedException e) {
                LOGGER.error("", e);
                throw new DaoRuntimeException(e);
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        LOGGER.error("Tried to clone connection pool");
        throw new CloneNotSupportedException();
    }
    protected Object readResolve() {
        return instance;
    }
}
