package com.company.dao;

import com.company.entity.appointment.SurgeryAppointment;

import java.util.List;

public interface SurgeryAppointmentDao extends GenericDao<SurgeryAppointment> {
    List<SurgeryAppointment> findByDiagnosisHistoryId(Integer diagnosisHistoryId);
}
