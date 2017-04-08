package model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model_dao.OrderDAO;
import model_dao.UserDAO;

public class Demo {

	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
		
//		User user = new User("Teodora", "Nakova", "talro@abv.bg", "talro123");
//		try {
//			UserDAO.getInstance().addUser(user);
//		} catch (SQLException e) {
//			System.out.println("Error adding user." + e.getMessage());
//		}
//		
//		User u = UserDAO.getInstance().getUser(1);
//		System.out.println(u);
//		
//		UserDAO.getInstance().validLogin(user, "talro@abv.bg", "talro123");
		ArrayList<Product> products = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Product p = new Product("Pizza " + i, 2);
			p.setProductId(i+1);
			products.add(p);
		}
		Order o = new Order(1, LocalDateTime.now());
		OrderDAO.getInstance().makeOrder(o, products);
		
	}
}
