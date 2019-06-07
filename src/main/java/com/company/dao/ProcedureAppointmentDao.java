package com.company.dao;

import com.company.entity.appointment.ProcedureAppointment;

import java.util.List;

public interface ProcedureAppointmentDao extends GenericDao<ProcedureAppointment> {
    List<ProcedureAppointment> findByDiagnosisHistoryId(Integer diagnosisHistoryId);
}
