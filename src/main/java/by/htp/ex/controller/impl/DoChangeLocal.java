package by.htp.ex.controller.impl;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoChangeLocal implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession(true);
        String local = request.getParameter(RequestParameterName.LOCAL);
        String url = (String) session.getAttribute(AttributeName.URL);
        if (url == null) {
            url = PageName.BASE_PAGE;
        }
        session.setAttribute(RequestParameterName.LOCAL, local);
        response.sendRedirect(url);
    }
}
