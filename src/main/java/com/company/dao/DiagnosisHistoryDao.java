package com.company.dao;

import com.company.entity.diagnosis.DiagnosisHistory;

import java.util.List;

public interface DiagnosisHistoryDao extends GenericDao<DiagnosisHistory> {
    List<DiagnosisHistory> getDiagnosisHistoryByPatientsId(Integer patientId);
}
