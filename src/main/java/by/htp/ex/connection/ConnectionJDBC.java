package by.htp.ex.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {
	private static Connection con;

	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news?useSSL=false&serverTimezone=UTC", "root",
					"12345");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public static Connection getConnection() {
		if (con == null) {
			con = createConnection();
		}
		return con;
	}

}
