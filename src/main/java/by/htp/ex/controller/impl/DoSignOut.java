package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoSignOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("user", "not active");
        response.sendRedirect(JspPageName.INDEX_PAGE);
    }
}
