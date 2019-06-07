package com.company.dao;

import com.company.entity.diagnosis.DiagnosisType;
import com.company.entity.user.Patient;

public interface PatientDao extends GenericDao<Patient>{
    DiagnosisType getDiagnosisType(Integer patientId);
}
