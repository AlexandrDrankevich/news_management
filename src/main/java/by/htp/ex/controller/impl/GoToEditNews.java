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

public class GoToEditNews implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(GoToEditNews.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(RequestParameterName.ID);
        try {
            String statusOfEdit = "active";
            News news = newsService.findById(Integer.parseInt(id));
            request.setAttribute(AttributeName.NEWS, news);
            request.setAttribute(AttributeName.EDIT_NEWS, statusOfEdit);
            request.getSession(true).setAttribute(AttributeName.URL, PageName.EDIT_NEWS_PAGE + id);
            request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }
}
