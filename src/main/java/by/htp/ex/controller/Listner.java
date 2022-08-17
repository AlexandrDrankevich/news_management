package by.htp.ex.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

public class Listner implements ServletContextListener {
	private static final Logger log = LogManager.getLogger(Listner.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConnectionPool.getInstance();
		} catch (ConnectionPoolException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

}
