package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.FactoryDao;
import com.company.dao.ProcedureDao;
import com.company.entity.Procedure;

import java.util.List;

public class ProcedureService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    public static class Holder {
        static final ProcedureService INSTANCE = new ProcedureService();
    }

    public static ProcedureService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Procedure> getAllProcedures() {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            ProcedureDao procedureDao = factoryDao.createProcedureDao(connection);
            return procedureDao.findAll();
        }
    }
}
