package com.company.dao.impl;

import com.company.controller.exception.MyApplicationException;
import com.company.dao.*;
import com.company.view.Exceptions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FactoryDaoImpl extends FactoryDao {
    private DataSource dataSource;


    public FactoryDaoImpl() {
        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");

            Context ic = new InitialContext();
            Context context = (Context) ic.lookup("java:comp/env");
            dataSource = (DataSource) context.lookup("jdbc/hospital");

        } catch (NamingException e) {
            throw new MyApplicationException(Exceptions.NAMING_EXCEPTION, e);
        }
    }

    @Override
    public ConnectionDao getConnection() {
        try {
            return new ConnectionDaoImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new MyApplicationException(Exceptions.SQL_ERROR, e);
        }
    }

    @Override
    public PatientDao createPatientDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new PatientDaoImpl(sqlConnection);
    }

    @Override
    public ProcedureDao createProcedureDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new ProcedureDaoImpl(sqlConnection);
    }

    @Override
    public StaffDao createStaffDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new StaffDaoImpl(sqlConnection);
    }

    @Override
    public DiagnosisHistoryDao createDiagnosisHistoryDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new DiagnosisHistoryDaoImpl(sqlConnection);
    }

    @Override
    public DiagnosisDao createDiagnosisDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new DiagnosisDaoImpl(sqlConnection);
    }

    @Override
    public DrugDao createDrugDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new DrugDaoImpl(sqlConnection);
    }

    @Override
    public SurgeryDao createSurgeryDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new SurgeryDaoImpl(sqlConnection);
    }

    @Override
    public DrugAppointmentDao createDrugAppointmentDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new DrugAppointmentDaoImpl(sqlConnection);
    }

    @Override
    public SurgeryAppointmentDao createSurgeryAppointmentDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new SurgeryAppointmentDaoImpl(sqlConnection);
    }

    @Override
    public ProcedureAppointmentDao createProcedureAppointmentDao(ConnectionDao connection) {
        ConnectionDaoImpl jdbcConnection = (ConnectionDaoImpl) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new ProcedureAppointmentDaoImpl(sqlConnection);
    }
}
