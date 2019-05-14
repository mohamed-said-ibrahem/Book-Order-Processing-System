package com.orderprocessingsystem.project.Model;

public class BookAuthors {

	private String ISBN;
	private String authorName;
	
	public BookAuthors() {
		super();
	}

	public BookAuthors(String iSBN, String authorName) {
		super();
		ISBN = iSBN;
		this.authorName = authorName;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
}
