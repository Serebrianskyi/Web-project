package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.DrugAppointmentDao;
import com.company.dao.FactoryDao;
import com.company.entity.appointment.DrugAppointment;

import java.util.List;

public class DrugAppointmentService {
    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final DrugAppointmentService INSTANCE = new DrugAppointmentService();
    }

    public static DrugAppointmentService getInstance() {
        return Holder.INSTANCE;
    }

    public List<DrugAppointment> getDrugAppointmentByDiagnosisHistoryId(Integer diagnosisHistoryId) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            DrugAppointmentDao drugAppointmentDao = factoryDao.createDrugAppointmentDao(connection);
            return drugAppointmentDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }

    public void createDrugAppointment (List<DrugAppointment> drugAppointments) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            connection.begin();
            DrugAppointmentDao drugAppointmentDao = factoryDao.createDrugAppointmentDao(connection);
            for (DrugAppointment drugAppointment : drugAppointments) {
                drugAppointmentDao.create(drugAppointment);
            }
            connection.commit();
        }
    }
}
