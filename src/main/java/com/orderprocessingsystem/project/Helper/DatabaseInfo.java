package com.orderprocessingsystem.project.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.orderprocessingsystem.project.Model.Book;
import com.orderprocessingsystem.project.Model.BookOrders;
import com.orderprocessingsystem.project.Model.BookPublisher;
import com.orderprocessingsystem.project.Model.BooksSold;
import com.orderprocessingsystem.project.Model.Users;

public class DatabaseInfo {

	private JdbcTemplate template;
	private String uname="";
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	public Boolean addPublisher(final Book book,final BookPublisher publisher) {	
		
		String data = "INSERT INTO BOOK_PUBLISHER ( `FName`, `LName`, `Address`, `Phone`, `Email`) VALUES("
		+"'"+publisher.getFname()+"'"+","
		+"'"+publisher.getLname()+"'"+","
		+"'"+publisher.getAddress()+"'"+","
		+publisher.getPhone()+","
		+"'"+publisher.getEmail()+"'"+")";
		int publisherDone = template.update(data);
		
		
		String data2 = "SELECT `PID` FROM `BOOK_PUBLISHER` WHERE `Email` =?";				
		final BookPublisher p = template.queryForObject(data2, new Object[]{publisher.getEmail()}, new BeanPropertyRowMapper<BookPublisher>(BookPublisher.class));
		
		System.out.println(p.getPID());
		
		String insertData = "INSERT INTO BOOK (`ISBN`, `Title`, `PID`, `Year`, `Price`, `Category`, `Threshold`, `Stock`) VALUES (?,?,?,?,?,?,?,?)";


	return template.execute(insertData,new PreparedStatementCallback<Boolean>() {
		@Override
		public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
		
		ps.setInt(1, book.getISBN());
		ps.setString(2, book.getTitle());
		ps.setInt(3, p.getPID());
		ps.setInt(4, book.getYear());
		ps.setDouble(5, book.getPrice());
		ps.setString(6, book.getCategory());
		ps.setInt(7, book.getThreshold());
		ps.setInt(8, book.getStock());
		return ps.execute();
	}
	
});
		
	}

	
	// SELECT BY id
	public Book bookByISBN(int id) {
		String sql = "SELECT * FROM `BOOK` WHERE ISBN=?";
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Book>(Book.class));
	}
	public Book bookByISBN2(String id) {
		String sql = "SELECT * FROM `BOOK` WHERE ISBN=?";
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Book>(Book.class));
	}
	
	
	
	public List<Book> bookByAuthorName(String test) {
		String sql = "SELECT BOOK.ISBN,BOOK.title,BOOK.pid,BOOK.year,BOOK.price,BOOK.category,BOOK.threshold,BOOK.stock from BOOK, BOOK_AUTHORS WHERE BOOK.ISBN = BOOK_AUTHORS.ISBN AND authorName LIKE '%"+test+"%'";
		System.out.println(sql);	
		return template.query(sql,new RowMapper<Book>(){
	
				@Override
				public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					
					Book b = new Book();
					b.setISBN(resultSet.getInt(1));
					b.setTitle(resultSet.getString(2));
					b.setPID(resultSet.getInt(3));
					b.setYear(resultSet.getInt(4));
					b.setPrice(resultSet.getDouble(5));
					b.setCategory(resultSet.getString(6));
					b.setThreshold(resultSet.getInt(7));
					b.setStock(resultSet.getInt(8));
					return b;
				}
				
			});
	
	}
	
	public List<Book> bookByPublisherName(String test) {
		String sql = "SELECT BOOK.ISBN,BOOK.title,BOOK.pid,BOOK.year,BOOK.price,BOOK.category,BOOK.threshold,BOOK.stock from BOOK, BOOK_PUBLISHER WHERE BOOK.PID = BOOK_PUBLISHER.PID AND BOOK_PUBLISHER.FName LIKE '%"+test+"%'";
		System.out.println(sql);	
		return template.query(sql,new RowMapper<Book>(){
	
				@Override
				public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					
					Book b = new Book();
					b.setISBN(resultSet.getInt(1));
					b.setTitle(resultSet.getString(2));
					b.setPID(resultSet.getInt(3));
					b.setYear(resultSet.getInt(4));
					b.setPrice(resultSet.getDouble(5));
					b.setCategory(resultSet.getString(6));
					b.setThreshold(resultSet.getInt(7));
					b.setStock(resultSet.getInt(8));
					return b;
				}
				
			});
	
	}
	
	
	// SELECT BY Category
	public List<Book> bookByCategory(String cat) {
		String sql = "SELECT * FROM `BOOK` WHERE Category="+"'"+cat+"'";
		
		return template.query(sql,new RowMapper<Book>(){

			@Override
			public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Book b = new Book();
				b.setISBN(resultSet.getInt(1));
				b.setTitle(resultSet.getString(2));
				b.setPID(resultSet.getInt(3));
				b.setYear(resultSet.getInt(4));
				b.setPrice(resultSet.getDouble(5));
				b.setCategory(resultSet.getString(6));
				b.setThreshold(resultSet.getInt(7));
				b.setStock(resultSet.getInt(8));
				return b;
			}
			
		});
	}
	
	// SELECT BY Title
	public List<Book> bookByTitle(String title) {
		String sql = "SELECT * FROM `BOOK` WHERE Title="+"'"+title+"'";
		
		return template.query(sql,new RowMapper<Book>(){

			@Override
			public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Book b = new Book();
				b.setISBN(resultSet.getInt(1));
				b.setTitle(resultSet.getString(2));
				b.setPID(resultSet.getInt(3));
				b.setYear(resultSet.getInt(4));
				b.setPrice(resultSet.getDouble(5));
				b.setCategory(resultSet.getString(6));
				b.setThreshold(resultSet.getInt(7));
				b.setStock(resultSet.getInt(8));
				return b;
			}
			
		});
	}

	
	
	public List<Map<String, Object>> viewSellingReport(){
		String readData = "select BOOK.Title,t1.Amount from BOOK join (select ISBN,SUM(Quantity) AS Amount from BOOKS_SOLD group by ISBN )t1 on t1.ISBN = BOOK.ISBN ORDER BY Amount DESC LIMIT 10";
		
		return template.query(readData,new RowMapper<Map<String, Object>>(){

			@Override
			public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("Title", resultSet.getString(1));
				item.put("Amount", resultSet.getInt(2));
				return item;
			}
			
		});
		
		
	}
	
	
	public List<Map<String, Object>> topCustomerReport(){
		String readData = "select USER.LName,t1.UID,t1.Amount from USER join(select UID,SUM(Quantity) AS Amount from BOOKS_SOLD group by UID )t1 on t1.UID = USER.ID ORDER BY Amount DESC LIMIT 5";
		
		return template.query(readData,new RowMapper<Map<String, Object>>(){

			@Override
			public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("LName", resultSet.getString(1));
				item.put("UID", resultSet.getInt(2));
				item.put("Amount", resultSet.getInt(3));

				return item;
			}
			
		});
		
		
	}
	
	public List<Map<String, Object>> totalSalesReport(){
		String readData = "select SUM(Price) as total_sales from BOOKS_SOLD";
		
		return template.query(readData,new RowMapper<Map<String, Object>>(){

			@Override
			public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Map<String, Object> item = new HashMap<String, Object>();
				item.put("total_sales", resultSet.getDouble(1));

				return item;
			}
			
		});
		
		
	}
	
	
//	 RETURN DATA FROM DATA BASE
	public List<Book> viewAll(){
		
		String readData = "SELECT * FROM `BOOK`";
		
		return template.query(readData,new RowMapper<Book>(){

			@Override
			public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Book b = new Book();
				b.setISBN(resultSet.getInt(1));
				b.setTitle(resultSet.getString(2));
				b.setPID(resultSet.getInt(3));
				b.setYear(resultSet.getInt(4));
				b.setPrice(resultSet.getDouble(5));
				b.setCategory(resultSet.getString(6));
				b.setThreshold(resultSet.getInt(7));
				b.setStock(resultSet.getInt(8));
				return b;
			}
			
		});
		
	}
	
	// Prepared Statement Example
	
	public Boolean insertPerson(Person p) {
		
		String insertData = "INSERT INTO person(`fname`,`lname`,`age`,`address`,`phone`) VALUES (?,?,?,?,?)";
		
		return template.execute(insertData,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				return ps.execute();
			}
			
		});
		
	}
	
	public Boolean updateBook(final Book b) {
		
	String insertData = "UPDATE BOOK SET `Title`=?,`PID`=?,`Year`=?,`Price`=?, `Category` =?,`Threshold` =?, `Stock` =? WHERE `ISBN`=?";
		
		return template.execute(insertData,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				ps.setString(1, b.getTitle());
				ps.setInt(2, b.getPID());
				ps.setInt(3, b.getYear());
				ps.setDouble(4, b.getPrice());
				ps.setString(5, b.getCategory());
				ps.setInt(6, b.getThreshold());
				ps.setInt(7, b.getStock());
				ps.setInt(8, b.getISBN());

				return ps.execute();
			}
			
		});
		
	}
	
	
	
	
	public Users getUserById(int id) {
		String sql = "SELECT * FROM `USER` WHERE ID=?";
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Users>(Users.class));
	}
	
	
	
	public Boolean deleteBook(final int isbn) {
		
		String insertData = "DELETE FROM `BOOK` WHERE `ISBN`=?";
		
		return template.execute(insertData,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				ps.setInt(1, isbn);

				return ps.execute();
			}
			
		});
		
	}
	
	
	
	public Boolean confirmOrder(final int isbn) throws SQLException {

		String sql = "Delete from BOOK_ORDERS where `ISBN`=?";
		
		return template.execute(sql,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				ps.setInt(1, isbn);

				return ps.execute();
			}
			
		});
		

	}

	public int placeOrder(int isbn, int qunatity) {

		String data = "SELECT * FROM `BOOK_ORDERS` WHERE ISBN =?";
		String sql = "";

		BookOrders p = null;
		try {
			p = template.queryForObject(data, new Object[]{isbn}, new BeanPropertyRowMapper<BookOrders>(BookOrders.class));

		} catch (Exception e) {
			sql= "Insert into BOOK_ORDERS Values ('" + isbn + "','" + qunatity + "')";
			System.out.println(sql);
			return template.update(sql);

		}
		


		if(p.getISBN()>=0) {
			sql = "update BOOK_ORDERS set quantity = quantity + '"+qunatity+"' where isbn = '"+isbn+"'";
			return template.update(sql);

		}else {
			sql= "Insert into BOOK_ORDERS Values ('" + isbn + "','" + qunatity + "')";

			return template.update(sql);
		}

}
	
	
	public Boolean promoteUserToAdmin(final int userId) {
		String data = "UPDATE `USER` SET `IsManager` = '1' WHERE `USER`.`ID`=?";
		
		return template.execute(data,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				ps.setInt(1, userId);

				return ps.execute();
			}
			
		});
}
	
					
	 public Boolean insertInToDatabase(final Person user) {
	        String insertedData = "INSERT INTO USER (NAME,FNAME,LNAME,PASSWORD,EMAIL,PHONENUMBER,SHIPPINGADDRESS,ISMANAGER) VALUES (?,?,?,?,?,?,?,?)";
	        return template.execute(insertedData,new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
	           // ps.setInt(1, user.getId());
	            ps.setString(1, user.getName());
	            ps.setString(2, user.getFname());
	            ps.setString(3, user.getLname());
	            ps.setString(4, user.getPassword());
	            ps.setString(5, user.getEmail());
	            ps.setString(6, user.getPhoneNumber());
	            ps.setString(7, user.getShippingAddress());
	            ps.setInt(8, user.getIsManager());
	            return ps.execute();
				}
			});
	    }
	    
	    /*this method is used within the registration stage to allow the user to has a unique user name 
	     * by checking if the user name that the user enters is already exists then the site will refuse it 
	     * and ask him to enter a new one*/
	    public boolean isUserNameExists(String name) {
	    	Person user;
	    	String select="SELECT * FROM USER WHERE NAME=\'"+name+"\'";
	    	try {
			 user= template.queryForObject(select, new RowMapper<Person>(){  
			    @Override  
			    public Person mapRow(ResultSet rs, int rownumber) throws SQLException {  
			    	Person e=new Person();  
			        e.setFname(rs.getString(2));    
			        return e;  
			    }  
			    });
	    	}catch(Exception e) {
	    		return false;
	    	}
	    	if(user.getFname()!=""&&user.getFname()!=null)/*means that this username is used before */
	    		return true;
	    	return false;
	        
	    }
	    
	    /*this method is used within the login stage to make sure that the provided user name and email
	     * exists in the database*/
	    public boolean isUserNameAndEmailExists(String name,String email) {
	    	Person user;
	    	String select="SELECT * FROM USER WHERE NAME=\'"+name+"\' AND EMAIL=\'"+email+"\'";
	    	try {
			 user= template.queryForObject(select, new RowMapper<Person>(){  
			    @Override  
			    public Person mapRow(ResultSet rs, int rownumber) throws SQLException {  
			    	Person e=new Person();  
			    	 e.setFname(rs.getString(2));
			        return e;  
			    }  
			    });
	    	}catch(Exception e) {
	    		return false;
	    	}
	    	if(user.getFname()!=""&&user.getFname()!=null)/*means that this username is used before */
	    		return true;
	    	return false;
	        
	    }
	

		public List<BooksSold> view(String uname){
			String read = "select * from `BOOKS_SOLD` where uname=\'"+uname+"\'";
			return template.query(read, new RowMapper<BooksSold>() {

				@Override
				public BooksSold mapRow(ResultSet rs, int rowNum) throws SQLException {
					BooksSold booksSold =new BooksSold();
					booksSold.setUname(rs.getString(1));
					booksSold.setISBN(rs.getInt(2));
					booksSold.setSellingDate(rs.getString(3));
					booksSold.setSellingTime(rs.getString(4));
					booksSold.setPrice(rs.getDouble(5));
					booksSold.setQuantity(rs.getInt(6));
					return booksSold;
				}
				
			});
		}
		

		public List<Book> vieweElementFromBook(int isbn){
			String read = "select * from book where isbn =\'" + isbn+"\'";
			return  template.query(read, new RowMapper<Book>() {

				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					Book book =new Book();
					book.setISBN(rs.getInt(1));
					book.setTitle(rs.getString(2));
					book.setPID(rs.getInt(3));
					book.setYear(rs.getInt(4));
					book.setPrice(rs.getDouble(5));
					book.setCategory(rs.getString(6));
					book.setThreshold(rs.getInt(7));
					book.setStock(rs.getInt(8));
					return book;
				}
				
			});
		}
		
		public void setUname (String name) {
			uname = name;
		}
		
		
		//INSERT INTO `books_sold`(`uname`, `ISBN`, `SellingDate`, `SellingTime`, `Price`, `Quantity`) VALUES ()
		public void addToCart(int isbn , int quantity) {
			double price;
			String Title;
			int oldStock = 0,newStock = 0;
			uname="sara";
			List<Book> tuplePrice = vieweElementFromBook(isbn);
			List<Book> tupleTitle = vieweElementFromBook(isbn);
			Title=tupleTitle.get(0).getTitle();
			price=tuplePrice.get(0).getPrice();
			List<Book> books=vieweElementFromBook(isbn);
			newStock = books.get(0).getStock()-quantity;
			String update ="UPDATE `book` SET `Stock`="+newStock+" WHERE isbn = \'"+isbn+"\'";
			Calendar cal = Calendar.getInstance();
		    Date calDate=cal.getTime();
		    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    String date=dateFormat.format(calDate);
		    String time=timeFormat.format(calDate);
			String insert = "INSERT INTO `books_sold`(`uname`, `ISBN`, `SellingDate`, `SellingTime`, `Price`, `Quantity`) VALUES ("+"\'"+uname+"\'"
		    +","+"\'"+isbn+"\'"+","+"\'"+date+"\'"+","+"\'"+time+"\'"+","+price+","+quantity+")";
			if(isBookExists(isbn , uname)) {
			template.update(update);
			template.update(insert);
			}
		}
		
		public void deleteCard(int isbn, String uname , int quantity) {
			
			String delete ="DELETE FROM `books_sold` WHERE ISBN = \'"+isbn+"\' AND uname="+"\'"+uname+"\'";
			List<Book> books=vieweElementFromBook(isbn);
			int newStock = books.get(0).getStock()+quantity;
			String update ="UPDATE `book` SET `Stock`="+newStock+" WHERE isbn = \'"+isbn+"\'";
			template.update(update);
			template.update(delete);
		}


		   public boolean isBookExists(int isbn , String uname) {
		    	BooksSold book;
		    	String select="SELECT * FROM books_sold WHERE isbn=\'"+isbn+"\' AND uname = \'"+uname+"\'";
		    	try {
		    		book= template.queryForObject(select, new RowMapper<BooksSold>(){  
				    @Override  
				    public BooksSold mapRow(ResultSet rs, int rownumber) throws SQLException {  
				    	BooksSold e=new BooksSold();  
				    	e.setUname(rs.getString(1));
				        e.setISBN(rs.getInt(2));
				        return e;  
				    }  
				    });
		    	}catch(Exception e) {
		    		return true;
		    	}
		    	if(book.getUname()!=uname&&book.getISBN()!=isbn)
		    		return false;
		    	return true;
		        
		    }
	
	
}
