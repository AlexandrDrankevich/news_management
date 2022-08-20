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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DoSIgnIn implements Command {

    private final UserService service = ServiceProvider.getInstance().getUserService();
    private static final Logger log = LogManager.getLogger(DoSIgnIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String go_to_news_list = "controller?command=go_to_news_list";
        String login = request.getParameter(RequestParameterName.LOGIN);
        String password = request.getParameter(RequestParameterName.PASSWORD);
        try {
            String role = service.signIn(login, password);
            if (!role.equals("guest")) {
                request.getSession(true).setAttribute("login", login);
                request.getSession(true).setAttribute("user", "active");
                request.getSession(true).setAttribute("role", role);
                request.getSession(true).setAttribute("url", go_to_news_list);
                response.sendRedirect(go_to_news_list);
            } else {
                request.getSession(true).setAttribute("user", "not active");
                request.getSession(true).setAttribute("url", go_to_news_list);
                response.sendRedirect("controller?command=go_to_base_page&AuthenticationError=wrong login or password");
            }
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(JspPageName.INDEX_PAGE);
        }
    }
}
