package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.util.date.Date;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO implements IUserDAO {
	private static final Logger log = LogManager.getRootLogger();
	private static final String authorizeDataSelection = "SELECT * FROM users WHERE login=? AND password=?";

	@Override
	public boolean logination(String login, String password) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(authorizeDataSelection)) {
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return false;
	}

	private static final String userRole = "SELECT roles.title FROM users inner join roles on users.roles_id=roles.id where users.login=? and users.password=?";

	public String getRole(String login, String password) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(userRole)) {
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("title");
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return "quest";
	}

	private static final String insertRegistrationData = "INSERT INTO users(login,password,registration_date,name,surname,birthday) values (?,?,?,?,?,?)";

	@Override
	public boolean registration(NewUserInfo user) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
				PreparedStatement ps = connection.prepareStatement(insertRegistrationData)) {
			if (isloginExist(connection, user.getLogin())) {
				return false;
			}
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, getDate());
			ps.setString(4, user.getName());
			ps.setString(5, user.getSurname());
			ps.setString(6, user.getBirthday());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return true;
	}

	private static final String checkLoginExist = "SELECT login FROM users WHERE login=?";

	private boolean isloginExist(Connection connection, String login) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(checkLoginExist)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}
	}

	private String getDate() {
		return Date.getDate();
	}
}
