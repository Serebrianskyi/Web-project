package com.company.controller.command;

import com.company.view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAppointmentsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return GlobalConstants.REST_SHOW_PATIENT_INFO + "?id=1";
    }
}
