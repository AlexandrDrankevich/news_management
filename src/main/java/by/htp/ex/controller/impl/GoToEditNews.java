package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		String id;
		id = request.getParameter("id");
		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute("news", news);
			request.setAttribute("editnews", "active");
		    request.getRequestDispatcher(JspPageName.BASELAYOUT_PAGE).forward(request, response);
				} catch ( ServiceException e) {
					log.error(e);
					response.sendRedirect(JspPageName.ERROR_PAGE);
		}
	}
}
