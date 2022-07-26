package by.htp.ex.controller.impl;

import by.htp.ex.controller.constant.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GoToAddNewsPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userRoleName = "admin";
		if (session == null) {
			response.sendRedirect(PageName.INDEX_PAGE);
			return;
		}
		if (!userRoleName.equals(session.getAttribute(AttributeName.USER_ROLE))) {
			response.sendRedirect(PageName.INDEX_PAGE);
			return;
		}
		String addNewsStatus = "active";
		session.setAttribute(AttributeName.URL, PageName.ADD_NEWS_PAGE);
		request.setAttribute(AttributeName.ADD_NEWS, addNewsStatus);
		request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
	}
}
