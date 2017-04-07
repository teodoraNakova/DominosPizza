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

public class UserDAO implements IDao {

	private static UserDAO instance;
	
	private UserDAO() {
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public synchronized void addUser(User u) throws SQLException {
		PreparedStatement st = DBManager.getInstance().getInsertStatement(getTableName(), getColumnNames());
		st.setString(1, u.getFirstName());
		st.setString(2, u.getLastName());
		st.setString(3, u.getEmail());
		st.setString(4, u.getPassword());
		st.execute();
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		u.setUserId(rs.getLong(1));
	}
	
	public User getUser(long primary) throws SQLException{
		PreparedStatement st = DBManager.getInstance().getSelectStatement(
				getTableName(), getColumnNames(), getPrimaryKeyName());
		st.setLong(1, primary);
		ResultSet rs = st.executeQuery();
		User user = null;
		while(rs.next()){
			user = new User(rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("email"),
					rs.getString("password"));
			long userId = rs.getLong("user_id");
			user.setUserId(userId);
		}
		return user;
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
		PreparedStatement st = DBManager.getInstance().getSelectStatement(getTableName(), getColumnNames(), email);
		ResultSet rs = st.executeQuery();
		if(rs.next()){
			String pass = rs.getString(4);
			System.out.println(pass);
			MessageDigest m;
			try {
				m = MessageDigest.getInstance("MD5");
				m.update(password.getBytes());
				byte[] digest = m.digest();
				String hashtext = DatatypeConverter.printHexBinary(digest).toLowerCase();
				return pass.equals(hashtext);
				
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Problem with hashing the password.");
			}
		}
		return false;
	}
	
	public String getTableName() {
		return "users";
	}
	
	public String[] getColumnNames() {
		return new String[] {
				"first_name", "last_name", "email", "password"};
	}
	
	public String getPrimaryKeyName() {
		return "user_id";
	}

	@Override
	public String[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

}
