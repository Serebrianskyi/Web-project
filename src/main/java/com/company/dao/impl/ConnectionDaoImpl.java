package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.ConnectionDao;
import com.company.view.Exceptions;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDaoImpl implements ConnectionDao {
    private Connection connection;
    private boolean inTransaction = false;

    public ConnectionDaoImpl(Connection connection) {
        super();
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }

    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }

    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }

    }
}
