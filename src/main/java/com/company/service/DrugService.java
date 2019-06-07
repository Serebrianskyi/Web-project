package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.DrugDao;
import com.company.dao.FactoryDao;
import com.company.entity.Drug;

import java.util.List;

public class DrugService {
    FactoryDao factoryDao = FactoryDao.getInstance();


    private static class Holder {
        static final DrugService INSTANCE = new DrugService();
    }

    public static DrugService getInstance() {
        return Holder.INSTANCE;
    }
    public List<Drug> getAllDrugs() {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            DrugDao drugDao = factoryDao.createDrugDao(connection);
            return drugDao.findAll();
        }
    }
}
