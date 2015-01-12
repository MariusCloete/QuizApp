package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CoreDAO {
	private Connection conn = null;
	protected Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					   "jdbc:postgresql://localhost:5432/Quiz","postgres", "vodoo2");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	protected void disconnect(){
		try {
			if (!conn.isClosed()){
				conn.close();
			}
			conn = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
