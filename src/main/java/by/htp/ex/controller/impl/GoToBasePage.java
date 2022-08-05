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

public class GoToBasePage implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> latestNews;
        try {
            latestNews = newsService.latestList(5);
            request.setAttribute("news", latestNews);
            request.getRequestDispatcher(JspPageName.BASELAYOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
