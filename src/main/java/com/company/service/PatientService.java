package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.FactoryDao;
import com.company.dao.PatientDao;
import com.company.entity.diagnosis.DiagnosisType;
import com.company.entity.user.Patient;

import java.util.List;
import java.util.Optional;


public class PatientService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final PatientService INSTANCE = new PatientService();
    }
    public static PatientService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Patient> getAllPatients() {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            PatientDao patientDao = factoryDao.createPatientDao(connection);
            return patientDao.findAll();
        }
    }

    public void createPatient(Patient patient) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            PatientDao patientDao = factoryDao.createPatientDao(connection);
            patientDao.create(patient);
        }
    }

    public Optional<Patient> getPatientById(Integer id) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            PatientDao patientDao = factoryDao.createPatientDao(connection);
            return patientDao.find(id);
        }
    }

    public boolean isPatientOnCure(int id) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            PatientDao patientDao = factoryDao.createPatientDao(connection);
            return patientDao.getDiagnosisType(id) == DiagnosisType.PRIMARY;
        }
    }

}

