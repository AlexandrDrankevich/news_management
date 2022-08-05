package by.htp.ex.dao;

import by.htp.ex.bean.News;

import java.util.List;


public interface INewsDAO {
    List<News> getList() throws NewsDAOException;

    List<News> getLatestsList(int count) throws NewsDAOException;

    News fetchById(int id) throws NewsDAOException;

    int addNews(News news) throws NewsDAOException;

    void updateNews(News news) throws NewsDAOException;

    void deleteNewses(String[] idNewses) throws NewsDAOException;
}
