package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.SurgeryAppointmentDao;
import com.company.entity.Surgery;
import com.company.entity.appointment.SurgeryAppointment;
import com.company.view.Exceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurgeryAppointmentDaoImpl implements SurgeryAppointmentDao {
    private Connection connection;

    public SurgeryAppointmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String SURGERY_ID = "surgery_id";
    private static final String SURGERY_NAME = "surgery_name";

    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";


    private static final String SELECT_FROM_SURGERIES_APPOINTMENT =
            "SELECT sa.id, diagnosis_history_id, surgery_id, name surgery_name\n" +
                    "FROM surgeries_appointment sa JOIN surgeries s\n" +
                    "ON sa.surgery_id = s.id\n" +
                    "WHERE sa.diagnosis_history_id = ?";
    private static final String INSERT_INTO_SURGERIES_APPOINTMENT =
            "INSERT INTO surgeries_appointment\n" +
                    "(diagnosis_history_id, surgery_id)\n" +
                    "VALUES(?, ?)";

    @Override
    public List<SurgeryAppointment> findByDiagnosisHistoryId(Integer diagnosisHistoryId) {
        List<SurgeryAppointment> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_SURGERIES_APPOINTMENT)) {
            query.setString(1, String.valueOf(diagnosisHistoryId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
        return result;
    }

    private SurgeryAppointment getEntityFromResultSet(ResultSet rs) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setSurgeryId(rs.getInt(SURGERY_ID));
        surgery.setName(rs.getString(SURGERY_NAME));
        return new SurgeryAppointment.Builder()
                .withSurgeryAppointmentId(rs.getInt(ID))
                .withHistoryId(rs.getInt(DIAGNOSIS_HISTORY_ID))
                .withSurgery(surgery)
                .build();
    }

    @Override
    public Optional<SurgeryAppointment> find(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SurgeryAppointment> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(SurgeryAppointment surgeryAppointment) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_SURGERIES_APPOINTMENT, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(surgeryAppointment.getHistoryId()));
            query.setString(2, String.valueOf(surgeryAppointment.getSurgery().getSurgeryId()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                surgeryAppointment.setSurgeryAppointmentId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public void update(SurgeryAppointment surgeryAppointment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }
}
