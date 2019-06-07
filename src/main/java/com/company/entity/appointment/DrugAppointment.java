package com.company.entity.appointment;

import com.company.entity.Drug;

public class DrugAppointment {
    private Integer drugAppointmentId;
    private Integer historyId;
    private Drug drug;
    private int unitsQuantity;
    private int dailyQuantity;
    private int daysQuantity;

    public static class Builder {
        private DrugAppointment drugAppointment = new DrugAppointment();

        public Builder withDrugAppointmentId(Integer drugAppointmentId) {
            drugAppointment.drugAppointmentId = drugAppointmentId;
            return this;
        }

        public Builder withHistoryId(Integer historyId) {
            drugAppointment.historyId = historyId;
            return this;
        }

        public Builder withDrug(Drug drug) {
            drugAppointment.drug = drug;
            return this;
        }

        public Builder withUnitQuantity(int unitsQuantity) {
            drugAppointment.unitsQuantity = unitsQuantity;
            return this;
        }

        public Builder withDailyQuantity(int dailyQuantity) {
            drugAppointment.dailyQuantity = dailyQuantity;
            return this;
        }

        public Builder withDaysQuantity(int daysQuantity) {
            drugAppointment.daysQuantity = daysQuantity;
            return this;

        }

        public DrugAppointment build() {
            return drugAppointment;
        }

    }

    public Integer getDrugAppointmentId() {
        return drugAppointmentId;
    }

    public void setDrugAppointmentId(Integer drugAppointmentId) {
        this.drugAppointmentId = drugAppointmentId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getUnitsQuantity() {
        return unitsQuantity;
    }

    public void setUnitsQuantity(int unitsQuantity) {
        this.unitsQuantity = unitsQuantity;
    }

    public int getDailyQuantity() {
        return dailyQuantity;
    }

    public void setDailyQuantity(int dailyQuantity) {
        this.dailyQuantity = dailyQuantity;
    }

    public int getDaysQuantity() {
        return daysQuantity;
    }

    public void setDaysQuantity(int daysQuantity) {
        this.daysQuantity = daysQuantity;
    }

    @Override
    public String toString() {
        return "DrugAppointment{" +
                "drugAppointmentId=" + drugAppointmentId +
                ", historyId=" + historyId +
                ", drug=" + drug +
                ", unitsQuantity=" + unitsQuantity +
                ", dailyQuantity=" + dailyQuantity +
                ", daysQuantity=" + daysQuantity +
                '}';
    }
}
