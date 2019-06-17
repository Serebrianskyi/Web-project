package com.company.controller.command;

import com.company.controller.exception.MyApplicationException;
import com.company.view.Attributes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class CommandWrapper implements Command {
    private static final Object ERROR = "error";
    private static final Logger LOGGER = LogManager.getLogger(CommandWrapper.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            return doExecute(request, response);
        } catch (MyApplicationException e) {
            LOGGER.error(e);
            request.setAttribute(Attributes.PAGE_TITLE, ERROR);
            throw e;
        }
    }

    abstract String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
