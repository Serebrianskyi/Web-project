package com.company.dao;

import com.company.controller.exception.MyApplicationException;
import com.company.view.Exceptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class FactoryDao {

    public abstract ConnectionDao getConnection();


    public abstract PatientDao createPatientDao(ConnectionDao connection);

    public abstract ProcedureDao createProcedureDao(ConnectionDao connectionDao);

    public abstract StaffDao createStaffDao(ConnectionDao connection);

    public abstract DiagnosisHistoryDao createDiagnosisHistoryDao(ConnectionDao connection);

    public abstract DiagnosisDao createDiagnosisDao(ConnectionDao connection);

    public abstract DrugDao createDrugDao(ConnectionDao connection);

    public abstract SurgeryDao createSurgeryDao(ConnectionDao connection);

    public abstract DrugAppointmentDao createDrugAppointmentDao(ConnectionDao connection);

    public abstract SurgeryAppointmentDao createSurgeryAppointmentDao(ConnectionDao connection);

    public abstract ProcedureAppointmentDao createProcedureAppointmentDao(ConnectionDao connection);


    private static FactoryDao instance;
    private static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    public static FactoryDao getInstance() {
        if (instance == null) {
        try {
            InputStream inputStream = FactoryDao.class.getResourceAsStream(DB_FILE);
            Properties dbProps = new Properties();
            dbProps.load(inputStream);
            String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
            instance = (FactoryDao) Class.forName(factoryClass).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
                throw new MyApplicationException(Exceptions.DAO_FACTORY_EXCEPTION, e);
            }

        }
        return instance;
    }

}
