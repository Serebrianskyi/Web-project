package com.company.entity.appointment;

import com.company.entity.Surgery;

public class SurgeryAppointment {
    private Integer surgeryAppointmentId;
    private Integer historyId;
    private Surgery surgery;

    public static class Builder {
        private SurgeryAppointment surgeryAppointment = new SurgeryAppointment();

        public Builder withSurgeryAppointmentId(Integer surgeryAppointmentId) {
            surgeryAppointment.surgeryAppointmentId = surgeryAppointmentId;
            return this;
        }

        public Builder withHistoryId(Integer historyId) {
            surgeryAppointment.historyId = historyId;
            return this;
        }

        public Builder withSurgery(Surgery surgery) {
            surgeryAppointment.surgery = surgery;
            return this;
        }

        public SurgeryAppointment build() {
            return surgeryAppointment;
        }
    }

    public Integer getSurgeryAppointmentId() {
        return surgeryAppointmentId;
    }

    public void setSurgeryAppointmentId(Integer surgeryAppointmentId) {
        this.surgeryAppointmentId = surgeryAppointmentId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

    @Override
    public String toString() {
        return "SurgeryAppointment{" +
                "surgeryAppointmentId=" + surgeryAppointmentId +
                ", historyId=" + historyId +
                ", surgery=" + surgery +
                '}';
    }
}
