package com.company.dao;

public interface ConnectionDao extends AutoCloseable {
    void begin();

    void commit();

    void rollback();

    void close();
}
