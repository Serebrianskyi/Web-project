package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.ProcedureDao;
import com.company.entity.Procedure;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcedureDaoImpl implements ProcedureDao {
    private Connection connection;

    public ProcedureDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String SELECT_FROM_PROCEDURES = "SELECT * FROM procedures";
    private static final String SELECT_PROCEDURE_BY_ID = "SELECT * FROM procedure WHERE id = ?";


    @Override
    public List<Procedure> findAll() {
        List<Procedure> procedures = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_PROCEDURES)) {

            while (resultSet.next()) {
                procedures.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return procedures;
    }


    @Override
    public Optional<Procedure> find(Integer id) {
        Optional<Procedure> result = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_PROCEDURE_BY_ID)){
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if(resultSet.next()) {
                Procedure patient = getEntityFromResultSet(resultSet);
                result = Optional.of(patient);
            }
        }catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private Procedure getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setProcedureId(resultSet.getInt(ID));
        procedure.setName(resultSet.getString(NAME));
        return procedure;
    }

    @Override
    public void create(Procedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Procedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
