package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.FactoryDao;
import com.company.dao.SurgeryDao;
import com.company.entity.Surgery;

import java.util.List;

public class SurgeryService {
    FactoryDao factoryDao = FactoryDao.getInstance();
    private static class Holder {
        static final SurgeryService INSTANCE = new SurgeryService();
    }

    public static SurgeryService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Surgery> getAllSurgeries() {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            SurgeryDao surgeryDao = factoryDao.createSurgeryDao(connection);
            return surgeryDao.findAll();
        }
    }
}
