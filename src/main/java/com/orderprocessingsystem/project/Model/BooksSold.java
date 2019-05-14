package com.orderprocessingsystem.project.Model;


public class BooksSold{

	private int UID;
	private String uname;
	private int ISBN;
	private String sellingDate;
	private String sellingTime;
	private double price;
	private int quantity;
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getSellingDate() {
		return sellingDate;
	}
	public void setSellingDate(String sellingDate) {
		this.sellingDate = sellingDate;
	}
	public String getSellingTime() {
		return sellingTime;
	}
	public void setSellingTime(String sellingTime) {
		this.sellingTime = sellingTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BooksSold(int uID, String uname, int iSBN, String sellingDate, String sellingTime, double price,
			int quantity) {
		super();
		UID = uID;
		this.uname = uname;
		ISBN = iSBN;
		this.sellingDate = sellingDate;
		this.sellingTime = sellingTime;
		this.price = price;
		this.quantity = quantity;
	}
	public BooksSold() {
		super();
	}
	


}
