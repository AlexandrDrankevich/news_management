package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.connection.ConnectionJDBC;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;

public class UserDAO implements IUserDAO {
	private int roleId;
	Connection con;


	@Override
	public boolean logination(String login, String password) throws DaoException {

		if (con == null) {
			con = ConnectionJDBC.getConnection();
		}

		String sql = "SELECT * FROM users WHERE login=? AND password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				roleId = rs.getInt("roles_id");
				return true;
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return false;

	}

	public String getRole(String login, String password) throws DaoException {
		String sql = "SELECT * FROM roles WHERE id=" + roleId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				return rs.getString("title");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return "quest";
	}

	@Override
	public boolean registration(NewUserInfo user) throws DaoException {

		String sql = "INSERT INTO users(login,password,name,surname,birthday) values (?,?,?,?,?)";

		try {
			if (con == null) {
				con = ConnectionJDBC.getConnection();
			}
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			ps.setString(5, user.getBirthday());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;

	}
}
