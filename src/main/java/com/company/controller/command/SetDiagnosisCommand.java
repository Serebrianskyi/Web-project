package com.company.controller.command;

import com.company.entity.diagnosis.Diagnosis;
import com.company.entity.diagnosis.DiagnosisType;
import com.company.service.DiagnosisService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SetDiagnosisCommand extends CommandWrapper {
    private static final String TITLE_DIAGNOSIS_SET = "title.diagnosis.set";

    private DiagnosisService diagnosisService = DiagnosisService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiagnosisType diagnosisType = DiagnosisType.valueOf(request.getParameter(Parameters.DIAGNOSIS_TYPE));
        request.getSession().setAttribute(Parameters.DIAGNOSIS_TYPE, diagnosisType);

        List<Diagnosis> diagnosesList = diagnosisService.getAllDiagnoses();
        request.setAttribute(Attributes.DIAGNOSIS_LIST, diagnosesList);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_DIAGNOSIS_SET);
        return Paths.DIAGNOSIS_JSP;
    }
}
