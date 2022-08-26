package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;
import by.htp.ex.controller.constant.PageName;
import by.htp.ex.controller.constant.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DoDeleteNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getLogger(DoDeleteNews.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] idNews = request.getParameterValues(RequestParameterName.ID);
		try {
			if (idNews == null) {
				response.sendRedirect(PageName.NEWS_LIST_PAGE);
				return;
			}
			newsService.delete(idNews);
			response.sendRedirect(PageName.NEWS_LIST_PAGE + "&deleteMessage=delete ok");
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(PageName.ERROR_PAGE);
		}
	}
}
