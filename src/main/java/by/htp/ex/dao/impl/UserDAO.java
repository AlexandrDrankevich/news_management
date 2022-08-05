package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserDAO implements IUserDAO {
    private int roleId;
    private String role;

    @Override
    public boolean logination(String login, String password) throws DaoException {
        String sql = "SELECT * FROM users WHERE login=? AND password=?";
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    roleId = rs.getInt("roles_id");
                    setRole(connection, roleId);
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return false;
    }

    private void setRole(Connection connection, int roleId) throws SQLException {
        String sql = "SELECT * FROM roles WHERE id=" + roleId;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql);) {
            if (rs.next()) {
                role = rs.getString("title");
            }
        }
    }

    public String getRole() {
        if (role != null) {
            return role;
        }
        return "quest";
    }

    @Override
    public boolean registration(NewUserInfo user) throws DaoException {
        String sql = "INSERT INTO users(login,password,registration_date,name,surname,birthday) values (?,?,?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, getDate());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());
            ps.setString(6, user.getBirthday());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return true;
    }

    private String getDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("GMT+3"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = dateTimeFormatter.format(zonedDateTime);
        System.out.print(date);
        return date;
    }
}
