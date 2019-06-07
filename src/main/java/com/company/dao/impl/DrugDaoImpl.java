package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.DrugDao;
import com.company.entity.Drug;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrugDaoImpl implements DrugDao {
    private Connection connection;

    public DrugDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String NAME = "name";

    private static final String SELECT_FROM_DRUGS = "SELECT * FROM drugs";
    private static final String SELECT_DRUG_BY_ID = "SELECT * FROM drugs WHERE id = ?";


    @Override
    public Optional<Drug> find(Integer id) {
        Optional<Drug> result = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_DRUG_BY_ID)){
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if(resultSet.next()) {
                Drug patient = getEntityFromResultSet(resultSet);
                result = Optional.of(patient);
            }
        }catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DRUGS)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }
    private Drug getEntityFromResultSet(ResultSet rs) throws SQLException {
        Drug drug = new Drug();
        drug.setDrugId(rs.getInt(ID));
        drug.setName(rs.getString(NAME));
        return drug;
    }


    @Override
    public void create(Drug drug) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Drug drug) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
