package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.FactoryDao;
import com.company.dao.SurgeryAppointmentDao;
import com.company.entity.appointment.SurgeryAppointment;

import java.util.List;

public class SurgeryAppointmentService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final SurgeryAppointmentService INSTANCE = new SurgeryAppointmentService();
    }

    public static SurgeryAppointmentService getInstance() {
        return Holder.INSTANCE;
    }

    public List<SurgeryAppointment> getSurgeryAppointmentByDiagnosisHistoryId(int diagnosisHistoryId) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            SurgeryAppointmentDao surgeryAppointmentDao = factoryDao.createSurgeryAppointmentDao(connection);
            return surgeryAppointmentDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }

    public void createSurgeryAppointment(List<SurgeryAppointment> surgeryAppointments) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            connection.begin();
            SurgeryAppointmentDao surgeryAppointmentDao = factoryDao.createSurgeryAppointmentDao(connection);
            for (SurgeryAppointment surgeryAppointment : surgeryAppointments) {
                surgeryAppointmentDao.create(surgeryAppointment);
            }
            connection.commit();
        }
    }

}
