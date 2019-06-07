package com.company.entity.appointment;

import com.company.entity.Procedure;

public class ProcedureAppointment {
    private Integer procedureAppointmentId;
    private Integer historyId;
    private Procedure procedure;
    private int daysQuantity;

    public static class Builder {
        private ProcedureAppointment procedureAppointment = new ProcedureAppointment();

        public Builder withProcedureAppointmentId(Integer procedureAppointmentId) {
            procedureAppointment.procedureAppointmentId = procedureAppointmentId;
            return this;
        }

        public Builder withHistoryId(Integer historyId) {
            procedureAppointment.historyId = historyId;
            return this;
        }

        public Builder withProcedure(Procedure procedure) {
            procedureAppointment.procedure = procedure;
            return this;
        }

        public Builder withDaysQuantity(int daysQuantity) {
            procedureAppointment.daysQuantity = daysQuantity;
            return this;

        }

        public ProcedureAppointment getProcedureAppointment() {
            return procedureAppointment;
        }

        public void setProcedureAppointment(ProcedureAppointment procedureAppointment) {
            this.procedureAppointment = procedureAppointment;
        }

        public ProcedureAppointment build() {
            return procedureAppointment;

        }

    }

    public Integer getProcedureAppointmentId() {
        return procedureAppointmentId;
    }

    public void setProcedureAppointmentId(Integer procedureAppointmentId) {
        this.procedureAppointmentId = procedureAppointmentId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public int getDaysQuantity() {
        return daysQuantity;
    }

    public void setDaysQuantity(int daysQuantity) {
        this.daysQuantity = daysQuantity;
    }

    @Override
    public String toString() {
        return "ProcedureAppointment{" +
                "procedureAppointmentId=" + procedureAppointmentId +
                ", historyId=" + historyId +
                ", procedure=" + procedure +
                ", daysQuantity=" + daysQuantity +
                '}';
    }
}

