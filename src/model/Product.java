package model;

public class Product {

	private long productId;
	private String name;
	private double price;
	private long ownerId;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
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
	
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
}

