package com.orderprocessingsystem.project.Model;


public class BookOrders {

	private int orderId;
	private int ISBN;
	private int quantity;
	
	public BookOrders() {
		super();
	}
	
	public BookOrders(int orderId, int iSBN, int quantity) {
		super();
		this.orderId = orderId;
		ISBN = iSBN;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
