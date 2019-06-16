package com.company.controller.command;

import com.company.entity.Drug;
import com.company.entity.appointment.DrugAppointment;
import com.company.service.DrugAppointmentService;
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

public class AddDrugAppointmentCommand extends CommandWrapper {
    private static final String TITLE_ADD_DRUGS_APPOINTMENT = "title.appointment.drugs.add";
    private static final String UNITS_QUANTITY = "drugUnitsQuantity";
    private static final String DAILY_QUANTITY = "drugDailyQuantity";
    private static final String DAYS_QUANTITY = "drugDaysQuantity";

    private DrugAppointmentService drugAppointmentService = DrugAppointmentService.getInstance();

    @Override
    String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));
        List<DrugAppointment> drugAppointments = getDrugAppointmentFromRequest(request, diagnosisHistoryId);
        drugAppointmentService.createDrugAppointment(drugAppointments);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ADD_DRUGS_APPOINTMENT);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + Paths.REST_SHOW_APPOINTMENTS + Parameters._DIAGNOSIS_HISTORY_ID);
        return Paths.REDIRECTED;
    }

    private List<DrugAppointment> getDrugAppointmentFromRequest(HttpServletRequest request, Integer diagnosisHistoryId) {
        List<DrugAppointment> drugAppointments = new ArrayList<>();
        Enumeration<String> parameters = request.getParameterNames();

        while (parameters.hasMoreElements()) {
            String parameterName = parameters.nextElement();
            if (parameterName.startsWith(UNITS_QUANTITY)) {
                int drugId = 0;
                try {
                    drugId = Integer.parseInt(parameterName.replaceAll(".*\\_", ""));
                } catch (NumberFormatException e) {
                }
                int unitsQuantity = 0;
                try {
                    unitsQuantity = Integer.parseInt(request.getParameter(parameterName));
                } catch (NumberFormatException e) {
                }
                if (unitsQuantity > 0) {
                    int dailyQuantity = 0;
                    int daysQuantity = 0;
                    try {
                        dailyQuantity = Integer.parseInt(request.getParameter(DAILY_QUANTITY + "_" + drugId));
                        daysQuantity = Integer.parseInt(request.getParameter(DAYS_QUANTITY + "_" + drugId));
                    } catch (NumberFormatException e) {
                    }
                    if (dailyQuantity > 0 && daysQuantity > 0) {
                        Drug drug = new Drug();
                        drug.setDrugId(drugId);
                        DrugAppointment drugAppointment = new DrugAppointment.Builder()
                                .withDrug(drug)
                                .withUnitQuantity(unitsQuantity)
                                .withDailyQuantity(dailyQuantity)
                                .withDaysQuantity(daysQuantity)
                                .withHistoryId(diagnosisHistoryId)
                                .build();
                        drugAppointments.add(drugAppointment);
                    }
                }
            }
        }
        return drugAppointments;
    }
}
