package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.FactoryDao;
import com.company.dao.ProcedureAppointmentDao;
import com.company.entity.appointment.ProcedureAppointment;

import java.util.List;

public class ProcedureAppointmentService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final ProcedureAppointmentService INSTANCE = new ProcedureAppointmentService();
    }

    public static ProcedureAppointmentService getInstance() {
        return Holder.INSTANCE;
    }

    public List<ProcedureAppointment> getProcedureAppointmentByDiagnosisHistoryId(Integer diagnosisHistoryId) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            ProcedureAppointmentDao procedureAppointmentDao = factoryDao.createProcedureAppointmentDao(connection);
            return procedureAppointmentDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }

    public void createProcedureAppointment(List<ProcedureAppointment> procedureAppointments) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            connection.begin();
            ProcedureAppointmentDao procedureAppointmentDao = factoryDao.createProcedureAppointmentDao(connection);
            for (ProcedureAppointment procedureAppointment : procedureAppointments) {
                procedureAppointmentDao.create(procedureAppointment);
            }
            connection.commit();
        }
    }
}
