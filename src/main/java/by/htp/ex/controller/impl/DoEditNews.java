package by.htp.ex.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.controller.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter(RequestParameterName.TITLE);
		String briefNews = request.getParameter(RequestParameterName.BRIEF_NEWS);
		String content = request.getParameter(RequestParameterName.CONTENT);
		String login = (String) request.getSession().getAttribute(RequestParameterName.LOGIN);
		String id = request.getParameter(RequestParameterName.ID);
		if (checkDataNews(id, title, briefNews, content)) {
			response.sendRedirect(JspPageName.INDEX_PAGE);
			return;
		}
		News news = new News(title, briefNews, content);
		try {
			newsService.update(news, login);
			response.sendRedirect("controller?command=go_to_news_list&message=News updated!");

		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(JspPageName.ERROR_PAGE);
		}

	}

	private boolean checkDataNews(String id, String title, String briefNews, String content) {
		return title == null || briefNews == null || content == null || id == null ? true : false;

	}
}
