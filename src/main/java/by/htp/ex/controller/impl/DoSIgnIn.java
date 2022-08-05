package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.controller.RequestParameterName;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoSIgnIn implements Command {

    private final UserService service = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login;
        String password;
        login = request.getParameter(RequestParameterName.LOGIN);
        password = request.getParameter(RequestParameterName.PASSWORD);
        if (!checkData(login, password)) {
            response.sendRedirect(JspPageName.INDEX_PAGE);
            return;
        }
        try {
            String role = service.signIn(login, password);
            if (!role.equals("guest")) {
                request.getSession(true).setAttribute("user", "active");
                request.getSession(true).setAttribute("role", role);
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                request.getSession(true).setAttribute("user", "not active");
                response.sendRedirect("controller?command=go_to_base_page&AuthenticationError=wrong login or password");
            }
        } catch (ServiceException e) {
            response.sendRedirect(JspPageName.INDEX_PAGE);
        }
    }

    private boolean checkData(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        return true;
    }
}
