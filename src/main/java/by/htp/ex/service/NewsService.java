package by.htp.ex.service;

import by.htp.ex.bean.News;

import java.util.List;

public interface NewsService {
    void save(News news)throws ServiceException;

    void find();

    void update();

    List<News> latestList(int count) throws ServiceException;

    List<News> list() throws ServiceException;

    News findById(int id) throws ServiceException;
}
