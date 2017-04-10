package model;

import java.util.ArrayList;

public class Product {

	private long productId;
	private String name;
	private double price;
	private long ownerId;
	private String category;
	private ArrayList<String> subproducts;
	
	public Product(String name, double price, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
		subproducts = new ArrayList<>();
	}

	public long getProductId() {
		return productId;
	}
	
	public long getOwnerId() {
		return ownerId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ArrayList<String> getSubproducts() {
		return subproducts;
	}
	
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

