package model;

import java.sql.SQLException;

import model_dao.UserDAO;

public class Demo {

	public static void main(String[] args) throws SQLException {
		
//		User user = new User("Teodora", "Nakova", "sudjunka@abv.bg", "Sudjunka123");
//		try {
//			UserDAO.getInstance().addUser(user);
//		} catch (SQLException e) {
//			System.out.println("Error adding user." + e.getMessage());
//		}
		
//		User u = UserDAO.getInstance().getUser(1);
//		System.out.println(u);
		
		UserDAO.getInstance().validLogin("sudjunka@abv.bg", "Sudjunka123");
	}
}
