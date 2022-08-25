package by.htp.ex.controller.impl;

import by.htp.ex.controller.constants.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constants.PageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddNewsPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addNewsStatus = "active";
        request.getSession(true).setAttribute(AttributeName.URL, PageName.ADD_NEWS_PAGE);
        request.setAttribute(AttributeName.ADD_NEWS, addNewsStatus);
        request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
    }
}
