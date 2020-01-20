package logic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static DatabaseConnection instance;
	private Connection connection;
	private String url = "jdbc:mysql://localhost/travelbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String username = "root";
	private String password = "password";
	
	private DatabaseConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username, password);
			
		} catch(ClassNotFoundException ex) {
			System.out.println("Database connection creation failed: " + ex.getMessage());
		}
		
	}
	
	public Connection getConnection() {		
		
		return this.connection;		
	}
	
	public static synchronized DatabaseConnection getInstance() throws SQLException {
		
		if(instance == null) {
			instance = new DatabaseConnection();
			
		} else if(instance.getConnection().isClosed()) {
			instance = new DatabaseConnection();
		}
		
		return instance;
	}

}
