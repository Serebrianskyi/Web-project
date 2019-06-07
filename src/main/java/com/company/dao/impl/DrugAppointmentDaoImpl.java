package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.DrugAppointmentDao;
import com.company.entity.Drug;
import com.company.entity.appointment.DrugAppointment;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrugAppointmentDaoImpl implements DrugAppointmentDao {
    private Connection connection;

    public DrugAppointmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String DRUG_ID = "drug_id";
    private static final String DRUG_NAME = "drug_name";

    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";
    private static final String UNITS_QUANTITY = "units_quantity";
    private static final String DAILY_QUANTITY = "daily_quantity";
    private static final String DAYS_QUANTITY = "days_quantity";

    private static final String SELECT_FROM_DRUGS_APPOINTMENT =
            "SELECT ad.id, diagnosis_history_id, drug_id, units_quantity, daily_quantity, days_quantity, name drug_name\n" +
                    "FROM drugs_appointment ad JOIN drugs d\n" +
                    "ON ad.drug_id = d.id\n" +
                    "WHERE ad.diagnosis_history_id = ?";

    private static final String INSERT_INTO_DRUGS_APPOINTMENT =
            "INSERT INTO assignations_drugs\n" +
                    "(diagnosis_history_id, drug_id, units_quantity, daily_quantity, days_quantity)\n" +
                    "VALUES(?, ?, ?, ?, ?)";

    @Override
    public List<DrugAppointment> findByDiagnosisHistoryId(Integer diagnosisHistoryId) {
        List<DrugAppointment> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DRUGS_APPOINTMENT)) {
            query.setString(1, String.valueOf(diagnosisHistoryId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, ex);
        }
        return result;
    }

    private DrugAppointment getEntityFromResultSet(ResultSet rs) throws SQLException {
        Drug drug = new Drug();
        drug.setDrugId(rs.getInt(DRUG_ID));
        drug.setName(rs.getString(DRUG_NAME));
        return new DrugAppointment.Builder()
                .withDrugAppointmentId(rs.getInt(ID))
                .withHistoryId(rs.getInt(DIAGNOSIS_HISTORY_ID))
                .withDrug(drug)
                .withUnitQuantity(rs.getInt(UNITS_QUANTITY))
                .withDailyQuantity(rs.getInt(DAILY_QUANTITY))
                .withDaysQuantity(rs.getInt(DAYS_QUANTITY))
                .build();
    }

    @Override
    public Optional<DrugAppointment> find(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DrugAppointment> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(DrugAppointment drugAppointment) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_DRUGS_APPOINTMENT, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(drugAppointment.getHistoryId()));
            query.setString(2, String.valueOf(drugAppointment.getDrug().getDrugId()));
            query.setString(3, String.valueOf(drugAppointment.getUnitsQuantity()));
            query.setString(4, String.valueOf(drugAppointment.getDailyQuantity()));
            query.setString(5, String.valueOf(drugAppointment.getDaysQuantity()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                drugAppointment.setDrugAppointmentId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public void update(DrugAppointment drugAppointment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
