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
import java.util.List;

public class ShowPatientsCommand extends CommandWrapper {

    private static final String TITLE_PATIENTS_SHOW = "title.patients.show";

    private PatientService patientService = PatientService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Patient> patients = patientService.getAllPatients();
        request.setAttribute(Parameters.ATTR_PATIENTS_LIST, patients);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_PATIENTS_SHOW);
        return Paths.PATIENTS_JSP;
    }
}
