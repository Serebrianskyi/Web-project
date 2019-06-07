package com.company.dao;

import com.company.entity.appointment.DrugAppointment;

import java.util.List;

public interface DrugAppointmentDao extends GenericDao<DrugAppointment>{
    List<DrugAppointment> findByDiagnosisHistoryId(Integer diagnosisHistoryId);
}
