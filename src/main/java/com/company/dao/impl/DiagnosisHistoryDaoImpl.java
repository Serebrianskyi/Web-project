package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.DiagnosisHistoryDao;
import com.company.entity.diagnosis.Diagnosis;
import com.company.entity.diagnosis.DiagnosisHistory;
import com.company.entity.diagnosis.DiagnosisType;
import com.company.entity.user.Staff;
import com.company.service.DiagnosisService;
import com.company.service.PatientService;
import com.company.service.StaffService;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiagnosisHistoryDaoImpl implements DiagnosisHistoryDao {
    private Connection connection;

    public DiagnosisHistoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String DATE = "diagnosis_date";
    private static final String TYPE = "type";

    private static final String DIAGNOSIS_ID = "diagnosis_id";
    private static final String DIAGNOSIS_NAME = "diagnosis_name";

    private static final String STAFF_ID = "staff_id";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";


    private static final String SELECT_FROM_DIAGNOSIS_HISTORY =
            "SELECT dh.id, diagnosis_date, type, staff_id, s.lastname, s.firstname, s.surname, s.role," +
                    " diagnosis_id, d.name diagnosis_name\n" +
                    "FROM diagnosis_history dh JOIN staff s\n" +
                    "ON dh.staff_id = s.id\n" +
                    "JOIN diagnosis d\n" +
                    "ON dh.diagnosis_id = d.id\n" +
                    "WHERE patient_id = ?\n" +
                    "ORDER BY diagnosis_date";

    private static final String INSERT_INTO_DIAGNOSIS_HISTORY =
            "INSERT INTO diagnosis_history(diagnosis_date, patient_id, staff_id, diagnosis_id, type)\n" +
                    "VALUES(?, ?, ?, ?, ?)";

    private PatientService patientService = PatientService.getInstance();
    private StaffService staffService = StaffService.getInstance();
    private DiagnosisService diagnosisService = DiagnosisService.getInstance();


    @Override
    public void create(DiagnosisHistory diagnosisHistory) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_DIAGNOSIS_HISTORY, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(diagnosisHistory.getDate()));
            query.setString(2, String.valueOf(diagnosisHistory.getPatientId()));
            query.setString(3, String.valueOf(diagnosisHistory.getStaff().getStaffId()));
            query.setString(4, String.valueOf(diagnosisHistory.getDiagnosis().getDiagnosisId()));
            query.setString(5, String.valueOf(diagnosisHistory.getDiagnosisType().name()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                diagnosisHistory.setHistoryId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public List<DiagnosisHistory> getDiagnosisHistoryByPatientsId(Integer patientId) {
        List<DiagnosisHistory> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DIAGNOSIS_HISTORY)) {
            query.setString(1, String.valueOf(patientId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private DiagnosisHistory getEntityFromResultSet(ResultSet rs) throws SQLException {
        Staff staff = new Staff.Builder()
                .withId(rs.getInt(STAFF_ID))
                .withLastName(rs.getString(LASTNAME))
                .withFirstName(rs.getString(FIRSTNAME))
                .withSurName(rs.getString(SURNAME))
                .withRole(Staff.Role.valueOf(rs.getString(ROLE)))
                .build();

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisId(rs.getInt(DIAGNOSIS_ID));
        diagnosis.setName(rs.getString(DIAGNOSIS_NAME));

        return new DiagnosisHistory.Builder()
                .withId(rs.getInt(ID))
                .withTimestamp(rs.getTimestamp(DATE))
                .withStaff(staff)
                .withDiagnosis(diagnosis)
                .withDiagnosisType(DiagnosisType.valueOf(rs.getString(TYPE)))
                .build();
    }

    @Override
    public Optional<DiagnosisHistory> find(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DiagnosisHistory> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(DiagnosisHistory diagnosisHistory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
