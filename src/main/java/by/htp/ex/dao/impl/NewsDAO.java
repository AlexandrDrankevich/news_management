package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.util.date.Date;

import java.awt.event.MouseWheelListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO implements INewsDAO {

	public static final String latestNews = "SELECT * FROM news order by id DESC limit ?";

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(latestNews)) {
			ps.setInt(1, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief"),
						rs.getString("content"), rs.getString("date")));
			}
		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
		return result;
	}

	public static final String allNews = "SELECT * FROM news order by id DESC";

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> result = new ArrayList<News>();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(allNews);
			while (rs.next()) {
				result.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief"),
						rs.getString("content"), rs.getString("date")));
			}
		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
		return result;
	}

	public static final String newsById = "SELECT * FROM news where id=?";

	@Override
	public News fetchById(int id) throws NewsDAOException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(newsById)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new News(rs.getInt("id"), rs.getString("title"), rs.getString("brief"), rs.getString("content"),
					rs.getString("date"));
		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
	}

	private static final String addNews = "INSERT INTO news(title,brief,content,date,reporter_id) values(?,?,?,?,?)";

	@Override
	public void addNews(News news, String login) throws NewsDAOException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(addNews)) {
			int userId = getUserId(connection, login);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBriefNews());
			ps.setString(3, news.getContent());
			ps.setString(4, getDate());
			ps.setInt(5, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
	}

	private String getDate() {
		return Date.getDate();
	}

	private static final String getrUserId = "SELECT id FROM users WHERE login=?";

	private int getUserId(Connection connection, String login) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(getrUserId)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		}
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

	public static final String deleteNewsById = "DELETE  FROM news where id=?;";

	@Override
	public void deleteNews(int id) throws NewsDAOException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(deleteNewsById)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new NewsDAOException(e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException(e);
		}
	}
}
