package model;

import java.time.LocalDate;

public class Order {

	private int orderId;
	private int userId;
	private LocalDate date;
	
	public Order(int userId, LocalDate date) {
		this.userId = userId;
		this.date = date;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getUserId() {
		return userId;
	}

	public LocalDate getDate() {
		return date;
	}
	
	
}
