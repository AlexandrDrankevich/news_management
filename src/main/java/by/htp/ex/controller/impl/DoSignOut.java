package by.htp.ex.controller.impl;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoSignOut implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userStatusNotActive = "not active";
		HttpSession session = request.getSession();
		session.setAttribute(AttributeName.USER, userStatusNotActive);
		session.setAttribute(AttributeName.URL, PageName.BASE_PAGE);
		session.removeAttribute(AttributeName.NEWS_COUNT);
		response.sendRedirect(PageName.INDEX_PAGE);
	}
}
