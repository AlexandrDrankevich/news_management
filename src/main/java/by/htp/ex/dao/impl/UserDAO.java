package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

import java.sql.*;

public class UserDAO implements IUserDAO {
    private int roleId;
    private Connection connection;

    @Override
    public boolean logination(String login, String password) throws DaoException {
        String sql = "SELECT * FROM users WHERE login=? AND password=?";
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                roleId = rs.getInt("roles_id");
                return true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return false;
    }

    public String getRole(String login, String password) throws DaoException {
        String sql = "SELECT * FROM roles WHERE id=" + roleId;
        try {
            Statement st = connection.createStatement();
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
        if (connection == null) {
            try {
                ConnectionPool instance = ConnectionPool.getInstance();
                connection = instance.takeConnection();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
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
