package by.htp.ex.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command{
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 News news;
	        String id;
	        id = request.getParameter("id");
	       
	            try {
					news = newsService.findById(Integer.parseInt(id));
				
	            request.setAttribute("news", news);
		response.sendRedirect("controller?command=go_to_news_list&message=Edit saved!");
	            } catch (NumberFormatException | ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
