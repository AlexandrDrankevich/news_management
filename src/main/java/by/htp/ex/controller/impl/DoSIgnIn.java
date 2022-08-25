package by.htp.ex.controller.impl;

import by.htp.ex.controller.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.PageName;
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

		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String userRoleName="guest";
		String userStatusActive="active";
		String userStatusNotActive="not active";
		try {
			String role = service.signIn(login, password);
			if (!role.equals(userRoleName)) {
				request.getSession(true).setAttribute(AttributeName.LOGIN, login);
				request.getSession(true).setAttribute(AttributeName.USER, userStatusActive);
				request.getSession(true).setAttribute(AttributeName.USER_ROLE, role);
				response.sendRedirect(PageName.NEWS_LIST_PAGE);
			} else {
				request.getSession(true).setAttribute(AttributeName.USER, userStatusNotActive);
				request.getSession(true).setAttribute(AttributeName.URL, PageName.BASE_PAGE);
				response.sendRedirect(PageName.BASE_PAGE+"&AuthenticationError=wrong login or password");
			}
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageName.INDEX_PAGE);
		}
	}
}
