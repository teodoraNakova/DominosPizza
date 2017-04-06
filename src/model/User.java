package model;

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String avatarLink;
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	} 
	
	public int getUserId() {
		return userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAvatarLink() {
		return avatarLink;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}
}
