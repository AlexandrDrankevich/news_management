package by.htp.ex.service;

import by.htp.ex.bean.News;

import java.util.List;

public interface NewsService {
	void save(News news, String login) throws ServiceException;

	void find();

	void update(News news, String login) throws ServiceException;

	void delete(int id) throws ServiceException;

	List<News> latestList(int count) throws ServiceException;

	List<News> list() throws ServiceException;

	News findById(int id) throws ServiceException;
}
