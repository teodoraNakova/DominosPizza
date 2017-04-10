package model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import validation.EmailSender;
import model_dao.OrderDAO;
import model_dao.UserDAO;

public class Demo {

	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
		
//		ArrayList<Product> products = new ArrayList<>();
//		for (int i = 0; i < 5; i++) {
//			Product p = new Product("Pizza " + i, 2);
//			p.setProductId(i+1);
//			products.add(p);
//		}
//		Order o = new Order(1, LocalDateTime.now());
//		OrderDAO.getInstance().makeOrder(o, products);
		
//		User user = new User("Ivan", "Zahariev", "ivanzahariev@abv.bg", "Ivancho123");
//		UserDAO.getInstance().addUser(user);
		EmailSender.sendValidationEmail("dominos.pizza.itt@gmail.com",
				"Dominos pizza verification code", "Please click on the following link: ");
	}
}
