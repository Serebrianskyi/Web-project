package com.company.entity.user;


public class Patient {
    private Integer patientId;
    private String lastName;
    private String firstName;
    private String surName;

    public static class Builder {
        private Patient patient = new Patient();


        public Builder withId(Integer id) {
            patient.patientId = id;
            return this;
        }

        public Builder withLastName(String lastName) {
            patient.lastName = lastName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            patient.firstName = firstName;
            return this;
        }

        public Builder withSurName(String surName) {
            patient.surName = surName;
            return this;
        }

        public Patient build() {
            return patient;

        }
    }

    public Integer getId() {
        return patientId;
    }

    public void setId(Integer id) {
        this.patientId = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
