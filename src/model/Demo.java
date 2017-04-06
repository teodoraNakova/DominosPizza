package model;

import java.sql.SQLException;

import model_dao.UserDAO;

public class Demo {

	public static void main(String[] args) {
		
		User user = new User("Teodora", "Nakova", "sudjunka@abv.bg", "Sudjunka123");
		try {
			UserDAO.getInstance().addUser(user);
		} catch (SQLException e) {
			System.out.println("Error adding user." + e.getMessage());
		}
		
	}
}
