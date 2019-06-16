package com.company.controller.command;

import com.company.entity.user.Patient;
import com.company.service.PatientService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPatientCommand extends CommandWrapper {
    private static final String PATIENT_ADD = "patient.add";

    private PatientService patientService = PatientService.getInstance();


    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter(Parameters.LASTNAME);
        String firstName = request.getParameter(Parameters.FIRSTNAME);
        String surName = request.getParameter(Parameters.SURNAME);

        Patient patient = new Patient.Builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withSurName(surName)
                .build();
        patientService.createPatient(patient);

        request.setAttribute(Attributes.PAGE_TITLE, PATIENT_ADD);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + Paths.REST_SHOW_PATIENTS);
        return Paths.REDIRECTED;
    }
}
