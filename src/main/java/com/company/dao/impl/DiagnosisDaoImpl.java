package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.DiagnosisDao;
import com.company.entity.diagnosis.Diagnosis;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiagnosisDaoImpl implements DiagnosisDao {
    private Connection connection;

    public DiagnosisDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String SELECT_FROM_DIAGNOSIS = "SELECT * FROM diagnosis";
    private static final String SELECT_FROM_DIAGNOSIS_BY_ID = "SELECT * FROM diagnosis WHERE id = ?";



    @Override
    public Optional<Diagnosis> find(Integer id) {
        Optional<Diagnosis> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DIAGNOSIS_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Diagnosis diagnosis = getEntityFromResultSet(resultSet);
                result = Optional.of(diagnosis);
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public List<Diagnosis> findAll() {
        List<Diagnosis> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DIAGNOSIS)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private Diagnosis getEntityFromResultSet(ResultSet rs) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisId(rs.getInt(ID));
        diagnosis.setName(rs.getString(NAME));
        return diagnosis;
    }
    @Override
    public void create(Diagnosis diagnosis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Diagnosis diagnosis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
