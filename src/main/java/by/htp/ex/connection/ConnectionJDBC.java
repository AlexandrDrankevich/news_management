package by.htp.ex.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {
	
public static Connection getConnection() throws ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = null;
	
	try {
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news?useSSL=false&serverTimezone=UTC", "root",
				"drankevich+11");
		
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	return con;
	

}
}
