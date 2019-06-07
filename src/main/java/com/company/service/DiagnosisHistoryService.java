package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.DiagnosisHistoryDao;
import com.company.dao.FactoryDao;
import com.company.entity.diagnosis.DiagnosisHistory;

import java.util.List;

public class DiagnosisHistoryService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final DiagnosisHistoryService INSTANCE = new DiagnosisHistoryService();
    }

    public static DiagnosisHistoryService getInstance() {
        return Holder.INSTANCE;
    }
    public List<DiagnosisHistory> getDiagnosisHistoryByPatientId(Integer id) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            DiagnosisHistoryDao diagnosisHistoryDao = factoryDao.createDiagnosisHistoryDao(connection);
            return diagnosisHistoryDao.getDiagnosisHistoryByPatientsId(id);
        }
    }
    public void createDiagnosisHistory(DiagnosisHistory diagnosisHistory) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            DiagnosisHistoryDao diagnosisHistoryDao = factoryDao.createDiagnosisHistoryDao(connection);
            diagnosisHistoryDao.create(diagnosisHistory);
        }
    }
}
