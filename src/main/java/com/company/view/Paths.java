package com.company.view;


public final class Paths {

    public static final String REDIRECTED = "REDIRECTED";

    private static final String PREFIX = "/WEB-INF/view";
    public static final String HOME_JSP = PREFIX + "/home.jsp";
    public static final String LOGIN_JSP = PREFIX + "/login.jsp";
    public static final String REGISTRATION_JSP = PREFIX + "/registration.jsp";
    public static final String PATIENT_INFO_JSP = PREFIX + "/patientInfo.jsp";
    public static final String PATIENTS_JSP = PREFIX + "/patients.jsp";
    public static final String ADD_PATIENT_FORM_JSP = PREFIX + "/addPatient.jsp";
    public static final String DIAGNOSIS_JSP = PREFIX + "/diagnosis.jsp";
    public static final String APPOINTMENTS_JSP = PREFIX + "/assignations.jsp";
    public static final String ADD_DRUGS_APPOINTMENTS_JSP = PREFIX + "/addDrugsAppointment.jsp";
    public static final String ADD_PROCEDURES_APPOINTMENTS_JSP = PREFIX + "/addProceduresAppointment.jsp";
    public static final String ADD_SURGERIES_APPOINTMENTS_JSP = PREFIX + "/addSurgeriesAppointment.jsp";


    public static final String SHOW_LOGIN_FORM = "/show_login_form";
    public static final String SHOW_REGISTRATION_FORM = "/show_registration_form";
    public static final String HOME = "/home";
    public static final String LOGIN = "/login";
    public static final String REGISTRATION = "/registration";
    public static final String SHOW_PATIENTS = "/show_patients";
    public static final String SHOW_PATIENT_INFO = "/show_patient_info";
    public static final String SHOW_ADD_PATIENT_FORM = "/show_add_patient_form";
    public static final String ADD_PATIENT = "/add_patient";
    public static final String SET_DIAGNOSIS = "/set_diagnosis";
    public static final String SHOW_APPOINTMENTS = "/show_appointments";
    public static final String ADD_DIAGNOSIS = "/add_diagnosis";
    public static final String SHOW_ADD_DRUGS_APPOINTMENT_FORM = "/show_add_drugs_appointment_form";
    public static final String ADD_DRUGS_APPOINTMENT = "/add_drugs_appointment";
    public static final String SHOW_ADD_PROCEDURES_APPOINTMENT_FORM = "/show_add_procedures_appointment_form";
    public static final String ADD_PROCEDURES_APPOINTMENT = "/add_procedures_appointment";
    public static final String SHOW_ADD_SURGERIES_APPOINTMENT_FORM = "/show_add_surgeries_appointment";
    public static final String ADD_SURGERIES_APPOINTMENT = "/add_surgeries_appointment";


    private static final String REST = "/rest";
    public static final String REST_HOME = REST + HOME;
    public static final String REST_SHOW_PATIENTS = REST + SHOW_PATIENTS;
    public static final String REST_SHOW_PATIENT_INFO = REST + SHOW_PATIENT_INFO;
    public static final String REST_SHOW_APPOINTMENTS = REST + SHOW_APPOINTMENTS;

}

