package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class DoRegistration implements Command {

    private final UserService service = ServiceProvider.getInstance().getUserService();
    private static final Logger log = LogManager.getLogger(UserService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(RequestParameterName.NAME);
        String surname = request.getParameter(RequestParameterName.SURNAME);
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        String birthday = request.getParameter(RequestParameterName.BIRTHDAY);
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        NewUserInfo user = new NewUserInfo(name, surname, login, hashPassword, birthday);
        try {
            boolean result = service.registration(user);
            if (result) {
                request.getSession(true).setAttribute(AttributeName.URL, PageName.BASE_PAGE);
                response.sendRedirect(PageName.BASE_PAGE + "&regMessage=Successful registration!");
            } else {
                request.getSession(true).setAttribute(AttributeName.URL, PageName.BASE_PAGE_WITH_REG_PARAMETER);
                response.sendRedirect(PageName.BASE_PAGE_WITH_REG_PARAMETER + "&messageLoginExist="
                        + request.getParameter(RequestParameterName.LOGIN));
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.INDEX_PAGE);
        }
    }
}
