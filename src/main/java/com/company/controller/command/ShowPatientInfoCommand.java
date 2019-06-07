package com.company.controller.command;

import com.company.entity.diagnosis.DiagnosisHistory;
import com.company.entity.user.Patient;
import com.company.service.DiagnosisHistoryService;
import com.company.service.PatientService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ShowPatientInfoCommand extends CommandWrapper {
    private static final String TITLE_PATIENT_INFO = "title.patient.info";

    private PatientService patientService = PatientService.getInstance();
    private DiagnosisHistoryService diagnosisHistoryService = DiagnosisHistoryService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(Parameters.ID));

        Optional<Patient> patient = patientService.getPatientById(id);
        patient.ifPresent(patientConsumer -> request.getSession().setAttribute(Attributes.PATIENT, patientConsumer));

        List<DiagnosisHistory> diagnosisHistoryList = diagnosisHistoryService.getDiagnosisHistoryByPatientId(id);
        request.getSession().setAttribute(Attributes.DIAGNOSIS_HISTORY_LIST, diagnosisHistoryList);

        boolean isPatientOnCure = patientService.isPatientOnCure(id);
        request.setAttribute(Attributes.ATTR_IS_PATIENT_ON_CURE, isPatientOnCure);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_PATIENT_INFO);
        return Paths.PATIENT_INFO_JSP;
    }
}
