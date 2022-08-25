package by.htp.ex.controller.impl;

import by.htp.ex.controller.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.PageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegistrationPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(AttributeName.URL, PageName.BASE_PAGE_WITH_REG_PARAMETER);
		response.sendRedirect(PageName.BASE_PAGE_WITH_REG_PARAMETER);
	}
}
