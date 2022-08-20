package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute("url", "controller?command=go_to_base_page&reg=reg");
        response.sendRedirect("controller?command=go_to_base_page&reg=reg");
    }
}
