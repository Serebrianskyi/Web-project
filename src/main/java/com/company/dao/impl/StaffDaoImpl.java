package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.StaffDao;
import com.company.entity.user.Staff;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffDaoImpl implements StaffDao {
    private Connection connection;

    public StaffDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static final String SELECT_STAFF_BY_LOGIN = "SELECT * FROM staff WHERE lower(email) = ?";
    private static final String SELECT_FROM_STAFF = "SELECT * FROM staff";
    private static final String SELECT_STAFF_BY_ID = "SELECT * FROM staff WHERE id = ?";
    private static final String INSERT_STAFF_INFO =
            "INSERT INTO staff (lastname, firstname, surname, role, email, password) VALUES (?, ?, ?, ?, ?, ?)";


    @Override
    public Optional<Staff> find(Integer id) {
        Optional<Staff> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Staff staff = getEntityFromResultSet(resultSet);
                result = Optional.of(staff);
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public List<Staff> findAll() {
        List<Staff> procedures = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_STAFF)) {

            while (resultSet.next()) {
                procedures.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return procedures;
    }

    @Override
    public void create(Staff staff) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_STAFF_INFO, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, staff.getLastName());
            query.setString(2, staff.getFirstName());
            query.setString(3, staff.getSurName());
            query.setString(4, staff.getRole().toString());
            query.setString(5, staff.getEmail());
            query.setString(6, staff.getPassword());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                staff.setStaffId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public Optional<Staff> getStaffByEmail(String email) {
        Optional<Staff> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_LOGIN)) {
            query.setString(1, email.toLowerCase());
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Staff staff = getEntityFromResultSet(resultSet);
                result = Optional.ofNullable(staff);
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private Staff getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Staff.Builder()
                .withId(rs.getInt(ID))
                .withLastName(rs.getString(LASTNAME))
                .withFirstName(rs.getString(FIRSTNAME))
                .withSurName(rs.getString(SURNAME))
                .withRole(Staff.Role.valueOf(rs.getString(ROLE)))
                .withEmail(rs.getString(EMAIL))
                .withPassword(rs.getString(PASSWORD))
                .build();
    }

    @Override
    public void update(Staff staff) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
