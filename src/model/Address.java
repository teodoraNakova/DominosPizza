package model;

public class Address {

	private int addressId;
	private String neighbourhood; 
	private String street;
	private String streetNumber; 
	private String postcode;
	private String city;
	private String phone; 
	private String bell;
	private int floor;
	private int buildingNumber;
	private int apartmentNumber;
	private String entrance;
	private int userId;
	
	public Address(String street, String streetNumber, String postcode, String phone, int floor) {
		this.street = street;
		this.streetNumber = streetNumber;
		this.postcode = postcode;
		this.phone = phone;
		this.floor = floor;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setBell(String bell) {
		this.bell = bell;
	}
	
	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAddressId() {
		return addressId;
	}
	
	public String getNeighbourhood() {
		return neighbourhood;
	}
	public String getStreet() {
		return street;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getCity() {
		return city;
	}
	public String getPhone() {
		return phone;
	}
	public String getBell() {
		return bell;
	}
	public int getFloor() {
		return floor;
	}
	public int getBuildingNumber() {
		return buildingNumber;
	}
	public int getApartmentNumber() {
		return apartmentNumber;
	}
	public String getEntrance() {
		return entrance;
	}
	public int getUserId() {
		return userId;
	}
	
	
}
