package by.htp.ex.controller.impl;

import by.htp.ex.controller.constants.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constants.PageName;
import by.htp.ex.controller.constants.RequestParameterName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoChangeLocal implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String local = request.getParameter(RequestParameterName.LOCAL);
        String url = (String) request.getSession(true).getAttribute(AttributeName.URL);

        if (request.getSession().getAttribute(AttributeName.URL) == null) {
            url = PageName.BASE_PAGE;
        }
        request.getSession(true).setAttribute(RequestParameterName.LOCAL, local);
        response.sendRedirect(url);
    }
}
