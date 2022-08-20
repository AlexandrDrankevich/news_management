package by.htp.ex.controller.impl;

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
            News news = newsService.findById(Integer.parseInt(id));
            request.setAttribute("news", news);
            request.setAttribute("presentation", "viewNews");
            request.getSession(true).setAttribute("url", "controller?command=go_to_view_news&id=" + id);
            request.getRequestDispatcher(JspPageName.BASELAYOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            log.error(e);
            response.sendRedirect(JspPageName.ERROR_PAGE);
        }
    }
}
