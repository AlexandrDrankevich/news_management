package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.RequestParameterName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String local = request.getParameter(RequestParameterName.LOCAL);
		String url = (String) request.getSession(true).getAttribute(AttributeName.URL);
		 
		if (request.getSession().getAttribute(AttributeName.URL) == null) {
			url="controller?command=go_to_base_page";
           }
		request.getSession(true).setAttribute(RequestParameterName.LOCAL, local);
		response.sendRedirect(url);
	}
}
