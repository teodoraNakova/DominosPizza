package model_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static DBManager instance;
	private Connection connection = null;
	
	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Problem loading the driver!");
		}
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dominos?user=user&password=user");
		} catch (SQLException e) {
			System.out.println("Unable to connect to Database" + e.getMessage());
		}
		
	}
	
	public static synchronized DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
}
