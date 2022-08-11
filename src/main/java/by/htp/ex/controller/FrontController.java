package by.htp.ex.controller;

import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CommandProvider provider = new CommandProvider();
	private static final Logger log = LogManager.getRootLogger();

	public FrontController() {
		super();
	}

	@Override
	public void destroy() {
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
		
	}
}
