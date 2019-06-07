package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.DiagnosisDao;
import com.company.dao.FactoryDao;
import com.company.entity.diagnosis.Diagnosis;

import java.util.List;
import java.util.Optional;

public class DiagnosisService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final DiagnosisService INSTANCE = new DiagnosisService();
    }

    public static DiagnosisService getInstance() {
        return Holder.INSTANCE;

    }
    public Optional<Diagnosis> getDiagnosisById(Integer id) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            DiagnosisDao diagnosisDao = factoryDao.createDiagnosisDao(connection);
            return diagnosisDao.find(id);
        }
    }

    public List<Diagnosis> getAllDiagnoses() {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            DiagnosisDao diagnosisDao = factoryDao.createDiagnosisDao(connection);
            return diagnosisDao.findAll();
        }
    }
}
