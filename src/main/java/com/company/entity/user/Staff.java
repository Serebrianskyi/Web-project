package com.company.entity.user;

public class Staff {
    private Integer staffId;
    private String lastName;
    private String firstName;
    private String surName;
    private Role role;
    private String email;
    private String password;


    public enum Role {
        DOCTOR, NURSE;
    }

    public static class Builder {
        private Staff staff = new Staff();

        public Builder withId(Integer id) {
            staff.staffId = id;
            return this;
        }

        public Builder withLastName(String lastName) {
            staff.lastName = lastName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            staff.firstName = firstName;
            return this;
        }

        public Builder withSurName(String surName) {
            staff.surName = surName;
            return this;
        }

        public Builder withRole(Role role) {
            staff.role = role;
            return this;
        }

        public Builder withEmail(String email) {
            staff.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            staff.password = password;
            return this;
        }

        public Staff build() {
            return staff;
        }
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
