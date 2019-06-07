package com.company.controller.command;

import com.company.entity.user.Staff;
import com.company.service.StaffService;
import com.company.view.Attributes;
import com.company.view.Parameters;
import com.company.view.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class RegisterCommand extends CommandWrapper {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String TITLE_HOME = "title.home";

    private StaffService staffService = StaffService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter(Parameters.FIRSTNAME);
        String lastName = request.getParameter(Parameters.LASTNAME);
        String surName = request.getParameter(Parameters.SURNAME);
        String role = request.getParameter(Parameters.ROLE);
        String email = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        if (firstName != null && lastName != null && surName != null && role != null
                && email != null && password != null) {

            Optional<Staff> staff = staffService.register(firstName, lastName, surName,
                    Staff.Role.valueOf(role), email, password);

            String contextPath = request.getContextPath();
            LOGGER.debug("contextPath: " + contextPath);

            if (staff.isPresent()) {
                request.getSession().setAttribute(Attributes.STAFF, staff.get());
                response.sendRedirect(contextPath + Paths.REST_SHOW_PATIENTS);
            }
        }
        return Paths.REDIRECTED;
    }
}
