package com.company.entity.diagnosis;

import com.company.entity.user.Staff;

import java.sql.Timestamp;

public class DiagnosisHistory {
    private Integer historyId;
    private Diagnosis diagnosis;
    private DiagnosisType diagnosisType;
    private Integer patientId;
    private Staff staff;
    private Timestamp date;

    public static class Builder{
        private DiagnosisHistory history = new DiagnosisHistory();

        public Builder withId(Integer id){
            history.historyId = id;
            return this;
        }

        public Builder withDiagnosis(Diagnosis diagnosis){
            history.diagnosis = diagnosis;
            return this;
        }
        public Builder withDiagnosisType(DiagnosisType diagnosisType){
            history.diagnosisType = diagnosisType;
            return this;
        }
        public Builder withPatientId(Integer patientId){
            history.patientId = patientId;
            return this;
        }
        public Builder withStaff(Staff staff){
            history.staff = staff;
            return this;
        }
        public Builder withTimestamp(Timestamp date){
            history.date = date;
            return this;
        }
        public DiagnosisHistory build(){
            return history;
        }
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public DiagnosisType getDiagnosisType() {
        return diagnosisType;
    }

    public void setDiagnosisType(DiagnosisType diagnosisType) {
        this.diagnosisType = diagnosisType;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "DiagnosisHistory{" +
                "historyId=" + historyId +
                ", diagnosis=" + diagnosis +
                ", diagnosisType=" + diagnosisType +
                ", patientId=" + patientId +
                ", staff=" + staff +
                ", date=" + date +
                '}';
    }
}

