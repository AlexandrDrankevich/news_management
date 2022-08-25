package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.PageName;
import by.htp.ex.controller.RequestParameterName;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DoAddNews implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(DoAddNews.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(RequestParameterName.TITLE);
        String briefNews = request.getParameter(RequestParameterName.BRIEF_NEWS);
        String content = request.getParameter(RequestParameterName.CONTENT);
        String login = (String) request.getSession().getAttribute(RequestParameterName.LOGIN);
        String newsDate = request.getParameter(RequestParameterName.DATE);

        News news = new News(title, briefNews, content, newsDate);
        try {
            newsService.save(news, login);
            response.sendRedirect(PageName.VIEW_NEWS + news.getIdNews() + "&newsMessage=News saved!");

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }

    }

}
