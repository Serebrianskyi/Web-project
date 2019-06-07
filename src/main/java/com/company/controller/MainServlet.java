package com.company.controller;

import com.company.controller.command.Command;
import com.company.controller.command.CommandsHolder;
import com.company.view.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/rest/*")
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);

    private CommandsHolder commandsHolder;

    public MainServlet() {
        super();
        commandsHolder = new CommandsHolder();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandKey = getCommandKey(request);
        LOGGER.debug(commandKey);
        Command command = commandsHolder.getCommand(commandKey);
        String viewPage = command.execute(request, response);

        if (!viewPage.equals(Paths.REDIRECTED)) {
            LOGGER.debug("FORWARD to " + viewPage);
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

    private String getCommandKey(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(".*/rest", "");
        return method + ":" + path;
    }
}

