package com.company.controller.command;

import com.company.entity.Procedure;
import com.company.entity.appointment.ProcedureAppointment;
import com.company.service.ProcedureAppointmentService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AddProcedureAppointmentCommand extends CommandWrapper {

    private static final String TITLE_ADD_PROCEDURES_APPOINTMENT = "title.appointment.procedure.add";
    private static final String DAYS_QUANTITY = "procedureDaysQuantity";

    private ProcedureAppointmentService procedureAppointmentService = ProcedureAppointmentService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<ProcedureAppointment> procedureAppointments = getProcedureAppointmentFromRequest(request, diagnosisHistoryId);
        procedureAppointmentService.createProcedureAppointment(procedureAppointments);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ADD_PROCEDURES_APPOINTMENT);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + Paths.REST_SHOW_APPOINTMENTS + Parameters._DIAGNOSIS_HISTORY_ID + diagnosisHistoryId);
        return Paths.REDIRECTED;
    }

    private List<ProcedureAppointment> getProcedureAppointmentFromRequest(HttpServletRequest request, int diagnosisHistoryId) {
        List<ProcedureAppointment> procedureAppointments = new ArrayList<>();
        Enumeration<String> parameters = request.getParameterNames();

        while (parameters.hasMoreElements()) {
            String parameterName = parameters.nextElement();
            if (parameterName.startsWith(DAYS_QUANTITY)) {
                int procedureId = 0;
                try {
                    procedureId = Integer.parseInt(parameterName.replaceAll(".*\\_", ""));
                } catch (NumberFormatException e) {
                }
                int daysQuantity = 0;
                try {
                    daysQuantity = Integer.parseInt(request.getParameter(parameterName));
                } catch (NumberFormatException e) {
                }
                if (daysQuantity > 0) {
                    Procedure procedure = new Procedure();
                    procedure.setProcedureId(procedureId);
                    ProcedureAppointment procedureAppointment = new ProcedureAppointment.Builder()
                            .withProcedure(procedure)
                            .withDaysQuantity(daysQuantity)
                            .withHistoryId(diagnosisHistoryId)
                            .build();
                    procedureAppointments.add(procedureAppointment);
                }
            }
        }
        return procedureAppointments;
    }

}
