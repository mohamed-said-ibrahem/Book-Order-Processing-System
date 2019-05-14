package com.orderprocessingsystem.project.Model;


public class Users {

	private int id;
	private String name;
	private String fname;
	private String lname;
	private String password;
	private String email;
	private String phoneNumber;
	private String shippingAddress;
	private int isManager;
	
	public Users() {
		super();
	}

	public Users(int id, String name, String fname, String lname, String password, String email, String phoneNumber,
			String shippingAddress, int isManager) {
		super();
		this.id = id;
		this.name = name;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.shippingAddress = shippingAddress;
		this.isManager = isManager;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public int getIsManager() {
		return isManager;
	}
	
	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}
	
	
}
