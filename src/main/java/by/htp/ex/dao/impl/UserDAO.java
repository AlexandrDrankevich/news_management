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

public class UserDAO implements IUserDAO{
	int roleId;
	Connection con;
	Statement st;
	@Override
	public boolean logination(String login, String password) throws DaoException {
		
		try {
			con=ConnectionJDBC.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="SELECT * FROM users WHERE login=\""+login+"\""+"AND password=\""+password+"\"";
		try {
			st =con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				roleId=rs.getInt("roles_id");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	
	public String getRole(String login, String password) {
		String sql="SELECT * FROM roles WHERE id="+roleId;
	try {
		 
		return st.executeQuery(sql).getString("title");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return "quest";
	}

	@Override
	public boolean registration(NewUserInfo user) throws DaoException  {
	     Random rand = new Random();
	        int value = rand.nextInt(1000);

	        //  if(value % 3 == 0) {
	        //   try {
	        //        throw new SQLException("stub exception");
	        //    }catch(SQLException e) {
	        //        throw new DaoException(e);
	        //      }
	        //  }else if (value % 2 == 0) {
	        //    return true;
	        //  }else {
	        if (value % 2 == 0) {
	            return true;}
	        return false;
	        // }
	    }
}
