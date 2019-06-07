package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.PatientDao;
import com.company.entity.diagnosis.DiagnosisType;
import com.company.entity.user.Patient;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDao {
    private Connection connection;

    public PatientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";
    public static final String DIAGNOSIS_TYPE = "type";

    private static final String SELECT_FROM_PATIENTS =
            "SELECT * FROM patients";
    private static final String SELECT_PATIENT_BY_ID =
            "SELECT * FROM patients WHERE id = ?";
    private static final String INSERT_INTO_PATIENTS =
            "INSERT INTO patients (lastname, firstname, surname) VALUES (?, ?, ?)";
    private static final String SELECT_PATIENT_STATUS =
            "SELECT type FROM diagnosis_history\n" +
                    "WHERE patient_id = ?\n" +
                    "ORDER BY diagnosis_date DESC\n" +
                    "LIMIT 1";



    @Override
    public Optional<Patient> find(Integer id) {
        Optional<Patient> result = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Patient patient = getEntityFromResultSet(resultSet);
                result = Optional.of(patient);
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_PATIENTS)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public void create(Patient patient) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_PATIENTS, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, patient.getLastName());
            query.setString(2, patient.getFirstName());
            query.setString(3, patient.getSurName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                patient.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public DiagnosisType getDiagnosisType(Integer patientId) {
        DiagnosisType result = DiagnosisType.NONE;
        try (PreparedStatement query = connection.prepareStatement(SELECT_PATIENT_STATUS)) {

            query.setString(1, String.valueOf(patientId));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = DiagnosisType.valueOf(resultSet.getString(DIAGNOSIS_TYPE));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private Patient getEntityFromResultSet(ResultSet rs) throws SQLException {
        return new Patient.Builder()
                .withId(rs.getInt(ID))
                .withFirstName(rs.getString(FIRSTNAME))
                .withLastName(rs.getString(LASTNAME))
                .withSurName(rs.getString(SURNAME))
                .build();
    }

    @Override
    public void update(Patient patient) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
