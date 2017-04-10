package model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
		
		User user = new User("Martin", "Binev", "martooo@abv.bg", "Marto123");
//		UserDAO.getInstance().addUser(user);
//		UserDAO.getInstance().registeredUsers.put(user.getEmail(), user);
//		HashMap<String, User> users = UserDAO.getInstance().registeredUsers;
//		for(Entry<String, User> e : users.entrySet()) {
//			System.out.println(e.getKey());
//			System.out.println(e.getValue());
//		}
//		System.out.println(users);
		LocalDateTime expireTime =  LocalDateTime.now().plusHours(1);
		System.out.println(expireTime);
		LocalDateTime now = LocalDateTime.now();
		
	}
}
