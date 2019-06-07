package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.ProcedureAppointmentDao;
import com.company.entity.Procedure;
import com.company.entity.appointment.ProcedureAppointment;
import com.company.view.Exceptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcedureAppointmentDaoImpl implements ProcedureAppointmentDao {
private static final Logger LOGGER = LogManager.getLogger(ProcedureAppointmentDaoImpl.class);
    private Connection connection;

    public ProcedureAppointmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";
    private static final String DAYS_QUANTITY = "days_quantity";

    private static final String PROCEDURE_ID = "procedure_id";
    private static final String PROCEDURE_NAME = "procedure_name";

    private static final String SELECT_FROM_PROCEDURES_APPOINTMENT =
            "SELECT pa.id, diagnosis_history_id, procedure_id, days_quantity, name procedure_name\n" +
                    "FROM assignations_procedures pa JOIN procedures p\n" +
                    "ON pa.procedure_id = p.id\n" +
                    "WHERE pa.diagnosis_history_id = ?";

    private static final String INSERT_INTO_PROCEDURES_APPOINTMENT =
            "INSERT INTO assignations_procedures\n" +
                    "(diagnosis_history_id, procedure_id, days_quantity)\n" +
                    "VALUES(?, ?, ?)";

    @Override
    public List<ProcedureAppointment> findByDiagnosisHistoryId(Integer diagnosisHistoryId) {
        List<ProcedureAppointment> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_PROCEDURES_APPOINTMENT)) {
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
    private ProcedureAppointment getEntityFromResultSet(ResultSet rs) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setProcedureId(rs.getInt(PROCEDURE_ID));
        procedure.setName(rs.getString(PROCEDURE_NAME));
        return new ProcedureAppointment.Builder()
                .withProcedureAppointmentId(rs.getInt(ID))
                .withHistoryId(rs.getInt(DIAGNOSIS_HISTORY_ID))
                .withProcedure(procedure)
                .withDaysQuantity(rs.getInt(DAYS_QUANTITY))
                .build();
    }

    @Override
    public Optional<ProcedureAppointment> find(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ProcedureAppointment> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(ProcedureAppointment procedureAppointment) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_PROCEDURES_APPOINTMENT, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(procedureAppointment.getHistoryId()));
            query.setString(2, String.valueOf(procedureAppointment.getProcedure().getProcedureId()));
            query.setString(3, String.valueOf(procedureAppointment.getDaysQuantity()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                procedureAppointment.setHistoryId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public void update(ProcedureAppointment procedureAppointment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
