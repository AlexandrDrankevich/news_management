package by.htp.ex.service.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

    @Override
    public List<News> latestList(int count) throws ServiceException {
        try {
            return newsDAO.getLatestsList(count);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> list() throws ServiceException {
        try {
            return newsDAO.getList();
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News findById(int id) throws ServiceException {
        try {
            return newsDAO.fetchById(id);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(News news, String login) throws ServiceException {
        try {
            newsDAO.addNews(news, login);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(String[] idNews) throws ServiceException {
        try {
            newsDAO.deleteNews(idNews);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(News news, String login) throws ServiceException {
        try {
            newsDAO.updateNews(news, login);
            ;
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }
}
