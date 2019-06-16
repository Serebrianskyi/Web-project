package com.company.view;

public interface GlobalConstants {
    String INDEX_JSP = "/index.jsp";

    String PREFIX = "/WEB-INF/com.company.view";

    String LOGIN_JSP = PREFIX + "/login.jsp";

    String PATIENT_INFO_JSP = PREFIX + "/patientInfo.jsp";

    String PATIENTS_JSP = PREFIX + "/patients.jsp";

    String ADD_PATIENT_FORM_JSP = PREFIX + "/addPatient.jsp";

    String DIAGNOSIS_JSP = PREFIX + "/diagnosis.jsp";

    String APPOINTMENTS_JSP = PREFIX + "/assignations.jsp";

    String ADD_APPOINTMENTS_JSP = PREFIX + "/addAppointment.jsp";



    String LOGIN = "/login";

    String SHOW_PATIENTS = "/show_patients";

    String SHOW_PATIENT_INFO = "/show_patient_info";

    String ADD_PATIENT_FORM = "/add_patient_form";

    String ADD_PATIENT = "/add_patient";

    String SET_DIAGNOSIS = "/set_diagnosis";

    String SHOW_APPOINTMENTS = "/show_appointments";

    String ADD_DIAGNOSIS = "/add_diagnosis";

    String ADD_APPOINTMENT_FORM = "/add_appointment_form";

    String ADD_APPOINTMENTS = "/add_appointment";


    String REST = "/rest";

    String REST_SHOW_PATIENTS = REST + SHOW_PATIENTS;

    String REST_SHOW_PATIENT_INFO = REST + SHOW_PATIENT_INFO;
}
