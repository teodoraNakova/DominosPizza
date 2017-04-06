package model_dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDAO {

	private static UserDAO instance;
	private static final HashMap<String, User> allUsers = new HashMap<>(); //email  - > user
	
	private UserDAO() {
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public synchronized void addUser(User u) throws SQLException {
		String sql = "INSERT INTO users	(first_name, last_name, email, password) VALUES(?, ?, ?, md5(?))";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, u.getFirstName());
		st.setString(2, u.getLastName());
		st.setString(3, u.getEmail());
		st.setString(4, u.getPassword());
		st.execute();
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		u.setUserId(rs.getLong(1));
		getAllUsers().put(u.getEmail(), u);
	}
	
	public HashMap<String, User> getAllUsers() throws SQLException{
		if(allUsers.isEmpty()){
			String sql = "SELECT user_id, first_name, last_name, email, password FROM users";
			PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				User u = new User(rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("email"),
						rs.getString("password"));
				u.setUserId(rs.getLong("user_id"));
				allUsers.put(u.getEmail(), u);
			}
		}
		return allUsers;
	}
	
	public boolean isEmailFree(String email) throws SQLException{
		String sql = "SELECT email FROM users WHERE email='"+email+"'";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {    
		    return true; 
		} 
		return false;
	}
	
	public synchronized boolean validLogin(String email, String password) throws SQLException{
		if(getAllUsers().containsKey(email)){
			MessageDigest m;
			try {
				m = MessageDigest.getInstance("MD5");
				m.update(password.getBytes());
				byte[] digest = m.digest();
				String hashtext = DatatypeConverter.printHexBinary(digest).toLowerCase();
				return allUsers.get(email).getPassword().equals(hashtext);
				
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Problem with hashing the password.");
			}
		}
		return false;
	}

}
