package by.htp.ex.controller.impl;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoSignOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userStatusNotActive = "not active";
        request.getSession(true).setAttribute(AttributeName.USER, userStatusNotActive);
        request.getSession(true).setAttribute(AttributeName.URL, PageName.BASE_PAGE);
        response.sendRedirect(PageName.INDEX_PAGE);
    }
}
