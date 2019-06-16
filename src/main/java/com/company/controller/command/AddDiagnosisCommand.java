package com.company.controller.command;

import com.company.entity.diagnosis.Diagnosis;
import com.company.entity.diagnosis.DiagnosisHistory;
import com.company.entity.diagnosis.DiagnosisType;
import com.company.entity.user.Patient;
import com.company.entity.user.Staff;
import com.company.service.DiagnosisHistoryService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class AddDiagnosisCommand implements Command {

    private static final String TITLE_DIAGNOSIS_ADD = "title.diagnosis.add";
    private DiagnosisHistoryService diagnosisHistoryService = DiagnosisHistoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DiagnosisType diagnosisType = (DiagnosisType)(request.getSession().getAttribute(Parameters.DIAGNOSIS_TYPE));

        Patient patient = (Patient) request.getSession().getAttribute(Attributes.PATIENT);
        int patientId = patient.getId();
        Staff staff = (Staff) request.getSession().getAttribute(Attributes.STAFF);

        int diagnosisId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_ID));
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosisId(diagnosisId);

        DiagnosisHistory diagnosisHistory = new DiagnosisHistory.Builder()
                .withPatientId(patientId)
                .withTimestamp(new Timestamp(new Date().getTime()))
                .withDiagnosis(diagnosis)
                .withDiagnosisType(diagnosisType)
                .withStaff(staff)
                .build();
        diagnosisHistoryService.createDiagnosisHistory(diagnosisHistory);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_DIAGNOSIS_ADD);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + Paths.REST_SHOW_PATIENT_INFO + Parameters._ID + patientId);
        return Paths.REDIRECTED;

    }

}
