package com.company.controller.command;

import com.company.entity.appointment.DrugAppointment;
import com.company.entity.appointment.ProcedureAppointment;
import com.company.entity.appointment.SurgeryAppointment;
import com.company.service.DrugAppointmentService;
import com.company.service.ProcedureAppointmentService;
import com.company.service.SurgeryAppointmentService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAppointmentCommand implements Command {
    private static final String TITLE_APPOINTMENTS_SHOW = "title.appointments.show";

    private DrugAppointmentService drugAppointmentService = DrugAppointmentService.getInstance();
    private ProcedureAppointmentService procedureAppointmentService = ProcedureAppointmentService.getInstance();
    private SurgeryAppointmentService surgeryAppointmentService = SurgeryAppointmentService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<DrugAppointment> drugAppointments =
                drugAppointmentService.getDrugAppointmentByDiagnosisHistoryId(diagnosisHistoryId);
        List<ProcedureAppointment> procedureAppointments =
                procedureAppointmentService.getProcedureAppointmentByDiagnosisHistoryId(diagnosisHistoryId);
        List<SurgeryAppointment> surgeryAppointments =
                surgeryAppointmentService.getSurgeryAppointmentByDiagnosisHistoryId(diagnosisHistoryId);

        request.setAttribute(Attributes.DIAGNOSIS_HISTORY_ID, diagnosisHistoryId);
        request.setAttribute(Attributes.DRUGS_APPOINTMENTS, drugAppointments);
        request.setAttribute(Attributes.PROCEDURES_APPOINTMENTS, procedureAppointments);
        request.setAttribute(Attributes.SURGERIES_APPOINTMENTS, surgeryAppointments);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_APPOINTMENTS_SHOW);
        return Paths.APPOINTMENTS_JSP;
    }

}
