package com.company.dao;

import com.company.entity.user.Staff;

import java.util.Optional;

public interface StaffDao extends GenericDao<Staff> {
    Optional<Staff> getStaffByEmail(String email);
}
