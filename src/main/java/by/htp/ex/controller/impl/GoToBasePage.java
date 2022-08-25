package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.constants.AttributeName;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.constants.PageName;
import by.htp.ex.controller.constants.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class GoToBasePage implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(GoToBasePage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> latestNews;
        int countNews = 5;
        try {
            latestNews = newsService.latestList(countNews);
            if (latestNews.isEmpty()) {
                latestNews = null;
            }
            checkParameter(request);
            request.setAttribute(AttributeName.NEWS, latestNews);
            request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }

    private void checkParameter(HttpServletRequest request) {
        if (request.getParameter(RequestParameterName.REGISTRATION_PAGE_URL) != null) {
            request.getSession(true).removeAttribute(AttributeName.URL);
        }
    }
}
