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

public class DoEditNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(NewsService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(RequestParameterName.TITLE);
        String briefNews = request.getParameter(RequestParameterName.BRIEF_NEWS);
        String content = request.getParameter(RequestParameterName.CONTENT);
        String login = (String) request.getSession().getAttribute(RequestParameterName.LOGIN);
        String id = request.getParameter(RequestParameterName.ID);
        String newsDate = request.getParameter(RequestParameterName.DATE);

        News news = new News(Integer.parseInt(id), title, briefNews, content, newsDate);
        try {
            newsService.update(news, login);
            request.getSession(true).setAttribute(AttributeName.URL, PageName.VIEW_NEWS + id);
            response.sendRedirect(PageName.VIEW_NEWS + id + "&newsMessage=News saved!");

        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }
}
