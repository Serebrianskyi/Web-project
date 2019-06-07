package com.company.service;

import com.company.dao.ConnectionDao;
import com.company.dao.FactoryDao;
import com.company.dao.StaffDao;
import com.company.dao.SurgeryDao;
import com.company.entity.Surgery;
import com.company.entity.user.Staff;

import java.util.List;
import java.util.Optional;

public class StaffService {

    FactoryDao factoryDao = FactoryDao.getInstance();

    private static class Holder {
        static final StaffService INSTANCE = new StaffService();
    }

    public static StaffService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Surgery> getAllSurgeries() {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            SurgeryDao surgeryDao = factoryDao.createSurgeryDao(connection);
            return surgeryDao.findAll();
        }
    }
    public Optional<Staff> login(String email, String password) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            StaffDao staffDao = factoryDao.createStaffDao(connection);
            return staffDao.getStaffByEmail(email)
                    .filter(staff -> password.equals(staff.getPassword()));
        }
    }

    public Optional<Staff> register (String firstName, String lastName,
                                     String surName, Staff.Role role, String email, String password) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            StaffDao staffDao = factoryDao.createStaffDao(connection);
            Staff staff = new Staff.Builder()
                    .withId(1)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSurName(surName)
                    .withRole(role)
                    .withEmail(email)
                    .withPassword(password)
                    .build();
            staffDao.create(staff);

            return staffDao.getStaffByEmail(email)
                    .filter(s -> password.equals(s.getPassword()));
        }
    }

    public Optional<Staff> getStaffById(Integer Id) {
        try (ConnectionDao connection = factoryDao.getConnection()) {
            StaffDao staffDao = factoryDao.createStaffDao(connection);
            return staffDao.find(Id);
        }
    }

}
