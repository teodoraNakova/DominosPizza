package model_dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		PreparedStatement st = DBManager.getInstance().getInsertStatement(getTableName(), getColumns());
		st.setString(1, u.getFirstName());
		st.setString(2, u.getLastName());
		st.setString(3, u.getEmail());
		st.setString(4, u.getPassword());
		st.execute();
		ResultSet rs = st.getGeneratedKeys();
		rs.next();
		u.setUserId(rs.getLong(1));
	}
	
	public synchronized User getUser(long primary) throws SQLException{
		PreparedStatement st = DBManager.getInstance().getSelectStatement(
				getTableName(), getColumns(), getPrimaryKeyName());
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
	
	public synchronized User findByEmail(String email) throws SQLException {
		PreparedStatement st = DBManager.getInstance()
		.getSelectStatement(getTableName(), getColumns(), "email");

        st.setString(1, email);
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
	
	public synchronized boolean isEmailFree(String email) throws SQLException{
		String sql = "SELECT email FROM users WHERE email='"+email+"'";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (!rs.next()) {    
		    return true; 
		} 
		return false;
	}
	
	public synchronized boolean validLogin(User user, String email, String password) 
			throws SQLException, NoSuchAlgorithmException {
		String userPassword = user.getPassword();
		System.out.println(userPassword);
		String hashedPassword = User.hashPassword(password);
		return userPassword.equals(hashedPassword);
	}
	
	@Override
	public String getTableName() {
		return "users";
	}
	
	@Override
	public String[] getColumns() {
		return new String[] {
				"first_name", "last_name", "email", "password"};
	}
	
	@Override
	public String getPrimaryKeyName() {
		return "user_id";
	}

}