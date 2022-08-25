package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.AttributeName;
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

public class GoToViewNews implements Command {

    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final Logger log = LogManager.getLogger(GoToViewNews.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter(RequestParameterName.ID);
        try {
        	String typeOfPresentation="viewNews";
            News news = newsService.findById(Integer.parseInt(id));
            request.setAttribute(AttributeName.NEWS, news);
            request.setAttribute(AttributeName.PRESENTATION, typeOfPresentation );
            request.getSession(true).setAttribute(AttributeName.URL, PageName.VIEW_NEWS + id);
            request.getRequestDispatcher(PageName.BASELAYOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(PageName.ERROR_PAGE);
        }
    }
}
