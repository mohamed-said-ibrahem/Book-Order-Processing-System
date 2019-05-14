package com.orderprocessingsystem.project.Model;


public class BookPublisher {

	private int PID;
	private String fname;
	private String lname;
	private String address;
	private int phone;
	private String email;
	
	public BookPublisher() {
		super();
	}

	public BookPublisher(int pID, String fname, String lname, String address, int phone, String email) {
		super();
		PID = pID;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
