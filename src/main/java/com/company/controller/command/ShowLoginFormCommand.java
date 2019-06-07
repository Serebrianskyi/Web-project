package com.company.controller.command;

import com.company.view.Attributes;
import com.company.view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowLoginFormCommand extends CommandWrapper{
    private static final String TITLE_LOGIN_FORM = "title.login.form";

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_LOGIN_FORM);
        return Paths.LOGIN_JSP;
    }
}
