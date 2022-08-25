package by.htp.ex.controller;

import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listener implements ServletContextListener {
    private static final Logger log = LogManager.getLogger(Listener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) throws RuntimeException{
        try {
            ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            log.error(e);
           throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) throws RuntimeException {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            log.error(e);
            throw new RuntimeException();
        }
    }

}
