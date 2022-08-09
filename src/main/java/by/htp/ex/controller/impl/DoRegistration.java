package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
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

public class DoRegistration implements Command {

    private final UserService service = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(RequestParameterName.NAME);
        String surname = request.getParameter(RequestParameterName.SURNAME);
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        String birthday = request.getParameter(RequestParameterName.BIRTHDAY);
        if (!checkData(login, password, name, surname, birthday)) {
            response.sendRedirect(JspPageName.INDEX_PAGE);
            return;
        }
        NewUserInfo user = new NewUserInfo(name, surname, login, password, birthday);
        try {
            boolean result = service.registration(user);
            if (result) {
                response.sendRedirect("controller?command=go_to_base_page&massage=Successful registration!");
            } else {
                response.sendRedirect("controller?command=go_to_base_page&massage=" + request.getParameter("login")
                        + " is already exist&reg=reg");
            }
        } catch (ServiceException e) {
            response.sendRedirect(JspPageName.INDEX_PAGE);
        }
    }

    private boolean checkData(String login, String password, String name, String surname, String birthday) {
        if (login == null || password == null || name == null || surname == null || birthday == null) {
            return false;
        }
        return true;
    }
}
