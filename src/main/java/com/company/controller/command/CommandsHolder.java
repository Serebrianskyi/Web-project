package com.company.controller.command;

import com.company.view.Attributes;
import com.company.view.Paths;

import java.util.HashMap;
import java.util.Map;

public class CommandsHolder {
    private static final String GET = "GET:";
    private static final String POST = "POST:";

    private Map<String, Command> commands;

    public CommandsHolder() {
        initCommands();
    }

    public void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put(GET + Paths.HOME, new HomeCommand());
                put(GET + Paths.SHOW_LOGIN_FORM, new ShowLoginFormCommand());
                put(GET + Paths.SHOW_REGISTRATION_FORM, new ShowRegisterFormCommand());
                put(GET + Paths.SHOW_ADD_PATIENT_FORM, new ShowAddPatientFormCommand());
                put(GET + Paths.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
                put(GET + Paths.SHOW_PATIENTS, new ShowPatientsCommand());
                put(GET + Paths.SET_DIAGNOSIS, new SetDiagnosisCommand());
                put(GET + Paths.SHOW_APPOINTMENTS, new ShowAppointmentCommand());


                put(POST + Paths.LOGIN, new LoginCommand());
                put(POST + Paths.REGISTRATION, new RegisterCommand());
                put(POST + Paths.SHOW_PATIENTS, new ShowPatientsCommand());
                put(POST + Paths.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
                put(POST + Paths.ADD_PATIENT, new AddPatientCommand());
                put(POST + Paths.ADD_DIAGNOSIS, new AddDiagnosisCommand());
                put(POST + Paths.ADD_DRUGS_APPOINTMENT, new AddDrugAppointmentCommand());
                put(POST + Paths.ADD_PROCEDURES_APPOINTMENT, new AddProcedureAppointmentCommand());
            }
        };
    }

    public Command getCommand(String key) {
        return commands.getOrDefault(key, (request, response) -> {
            request.setAttribute(Attributes.PAGE_TITLE, "title.home");
            response.sendRedirect(Paths.REST_HOME);
            return Paths.REDIRECTED;
        });
    }
}
