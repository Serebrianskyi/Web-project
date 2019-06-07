package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.SurgeryDao;
import com.company.entity.Surgery;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurgeryDaoImpl implements SurgeryDao {
    private Connection connection;

    public SurgeryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String SELECT_FROM_SURGERIES = "SELECT * FROM surgeries";
    private static final String SELECT_SURGERIES_BY_ID = "SELECT * FROM surgeries WHERE id = ?";

    @Override
    public Optional<Surgery> find(Integer id) {
        Optional<Surgery> result = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_SURGERIES_BY_ID)){
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if(resultSet.next()) {
                Surgery patient = getEntityFromResultSet(resultSet);
                result = Optional.of(patient);
            }
        }catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public List<Surgery> findAll() {
        List<Surgery> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_SURGERIES)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private Surgery getEntityFromResultSet(ResultSet rs) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setSurgeryId(rs.getInt(ID));
        surgery.setName(rs.getString(NAME));
        return surgery;
    }

    @Override
    public void create(Surgery surgery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Surgery surgery) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
