package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToNewsList implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		try {
			newsList = newsService.list();
			if(newsList.isEmpty()) {
				newsList=null;
			}
			request.setAttribute("news", newsList);
			request.setAttribute("presentation", "newsList");

			request.getRequestDispatcher(JspPageName.BASELAYOUT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(JspPageName.ERROR_PAGE);
		}
	}
}
