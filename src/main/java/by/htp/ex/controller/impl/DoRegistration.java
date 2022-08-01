package by.htp.ex.controller.impl;

import java.io.IOException;

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

public class DoRegistration implements Command {

	private final UserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter(RequestParameterName.NAME);
		String surname = request.getParameter(RequestParameterName.SURNAME);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String birthday = request.getParameter(RequestParameterName.BIRTHDAY);
		if (!checkData(email, password, name, surname, birthday)) {
			response.sendRedirect("index.jsp");
			return;
		} 
			NewUserInfo user = new NewUserInfo(name, surname, email, password, birthday);

			try {
				boolean result = service.registration(user);
				if (result) {
					response.sendRedirect("index.jsp");
				} else {

					response.sendRedirect("controller?command=go_to_base_page&massage=" + request.getParameter("email")
							+ " is already exist&reg=reg");
				}
			} catch (ServiceException e) {
				response.sendRedirect("index.jsp");
			}
		}
	
	private boolean checkData(String email,String password,String name,String surname, String birthday) {
		if (email == null || password == null || name == null || surname == null || birthday == null) {
			return false;
	} return true;
	}
}
