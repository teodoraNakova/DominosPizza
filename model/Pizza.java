package model;

public class Pizza {

	private int pizzaId;
	private String name;
	private double price; //
	private int crustId;
	private int sizeId;
	
	public Pizza(String name, double price, int crustId, int sizeId) {
		this.name = name;
		this.price = price;
		this.crustId = crustId;
		this.sizeId = sizeId;
	} 
	
	public int getPizzaId() {
		return pizzaId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getCrustId() {
		return crustId;
	}

	public int getSizeId() {
		return sizeId;
	}
	
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
}
