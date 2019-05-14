package com.orderprocessingsystem.project.Model;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

	@NotNull 
	private int ISBN;
	@NotNull @Pattern(regexp="^[A-Za-z0-9]*$") @Size(min = 1)
	private String title;
	private int year;
	@NotNull 
	private double price;
	private String category;
	@NotNull 
	private int threshold;
	@NotNull 
	private int stock;
	@NotNull @Pattern(regexp="^[A-Za-z0-9]*$") @Size(min = 2)
	private String publisherFname;
	@NotNull @Pattern(regexp="^[A-Za-z0-9]*$") @Size(min = 2)
	private String publisherLname;
	@NotNull @Pattern(regexp="^[A-Za-z0-9]*$") @Size(min = 5)
	private String publisherAddress;
	@NotNull 
	private int publisherPhone;
    @Pattern(regexp=".+@.+\\.[a-z]+")
	private String publisherEmail;
	@NotNull
	private int PID;
	
	public Book() {
		super();
	}
	
	public Book(int iSBN, String title, int year, double price, String category, int threshold, int stock,
			String publisherFname, String publisherLname, String publisherAddress, int publisherPhone,
			String publisherEmail, int pID) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.year = year;
		this.price = price;
		this.category = category;
		this.threshold = threshold;
		this.stock = stock;
		this.publisherFname = publisherFname;
		this.publisherLname = publisherLname;
		this.publisherAddress = publisherAddress;
		this.publisherPhone = publisherPhone;
		this.publisherEmail = publisherEmail;
		PID = pID;
	}

	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getPublisherFname() {
		return publisherFname;
	}
	public void setPublisherFname(String publisherFname) {
		this.publisherFname = publisherFname;
	}
	public String getPublisherLname() {
		return publisherLname;
	}
	public void setPublisherLname(String publisherLname) {
		this.publisherLname = publisherLname;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	public int getPublisherPhone() {
		return publisherPhone;
	}
	public void setPublisherPhone(int publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	public String getPublisherEmail() {
		return publisherEmail;
	}
	public void setPublisherEmail(String publisherEmail) {
		this.publisherEmail = publisherEmail;
	}
	public int getPID() {
		return PID;
	}
	public void setPID(int pID) {
		PID = pID;
	}

	
	
}