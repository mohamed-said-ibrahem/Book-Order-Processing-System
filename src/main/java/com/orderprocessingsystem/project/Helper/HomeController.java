package com.orderprocessingsystem.project.Helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.orderprocessingsystem.project.Model.Book;
import com.orderprocessingsystem.project.Model.BookOrders;
import com.orderprocessingsystem.project.Model.BookPublisher;
import com.orderprocessingsystem.project.Model.BooksSold;
import com.orderprocessingsystem.project.Model.Search;
import com.orderprocessingsystem.project.Model.Users;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	DatabaseInfo di;
	
	static int id;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/addBookToCart")
	public ModelAndView add(@ModelAttribute("b") BooksSold b) {
		int quan =b.getQuantity();
		di.addToCart(1, quan);;
		return new ModelAndView("/Home2");
	}
	
	@RequestMapping(value = "/getCard")
	public ModelAndView viewCards() {
		List<BooksSold> list=di.view("sara");
		return new ModelAndView("viewCard","list",list);
	}
	
	@RequestMapping(value="/deleteFromCard/{isbn}/{quantity}")
	public ModelAndView deleteElement(@PathVariable int isbn,@PathVariable int quantity) {
		System.out.println(isbn);
		di.deleteCard(isbn,"sara",quantity);
		List<BooksSold> list=di.view("sara");
		return new ModelAndView("viewCard","list",list);
	}
	
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public ModelAndView test() {
		
		return new ModelAndView("Home2");	
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView mainPage() {
		
		return new ModelAndView("AddNewBook","command",new Book());	
	}
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("project") @Validated Book newBook, BindingResult resultBook) {

			System.out.println(resultBook.getAllErrors());
	      if (resultBook.hasErrors()) {
				
				return "ErrorPage";
			}else {
	    	  BookPublisher newBookPublisher = new BookPublisher();
	    	  newBookPublisher.setFname(newBook.getPublisherFname());
	    	  newBookPublisher.setLname(newBook.getPublisherLname());
	    	  newBookPublisher.setAddress(newBook.getPublisherAddress());
	    	  newBookPublisher.setPhone(newBook.getPublisherPhone());
	    	  newBookPublisher.setEmail(newBook.getPublisherEmail());
	    	  di.addPublisher(newBook ,newBookPublisher);

			}
	      
	      return ("Home");

	}
	
	@RequestMapping(value = "/project/viewperson")
	public ModelAndView viewPerson() {
		return new ModelAndView("Home");	
	}
	
	@RequestMapping(value = "/placeOrder")
	public ModelAndView placeOrder() {
		return new ModelAndView("PlaceOrder","command",new BookOrders());	
	}
	
	@RequestMapping(value = "/placeOrderProcess", method = RequestMethod.POST)
	public ModelAndView placeOrderProcess(@ModelAttribute("project") @Validated BookOrders b, BindingResult result) {
  
		if (result.hasErrors()) {
	    		System.out.println(result.getAllErrors());
	  			return new ModelAndView("ErrorPage");
	  		}
	      try {
	      di.placeOrder(b.getISBN(), b.getQuantity());
			return new ModelAndView("redirect:/sucessplaceorder");
			}catch (Exception e) {
	  			return new ModelAndView("ErrorPage");
			}
	}
	
	@RequestMapping(value = "/sucessplaceorder" ,method = RequestMethod.GET)
	public ModelAndView successPlaceOrder() {

		return new ModelAndView("success2");
		
}
	
	@RequestMapping(value = "/search")
	public ModelAndView searchBook2() {
		return new ModelAndView("SearchBook","command",new Search());	
	}
	
	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	public ModelAndView searchBook(@ModelAttribute("project") @Validated Search search, BindingResult result) {

		
      if (result.hasErrors()) {
  		System.out.println(result.getAllErrors());
			return new ModelAndView("ErrorPage");
		}

		Search s =  new Search();
		s.setValue(search.getValue());
		String searchWord = s.getValue();
		
		try {
		Book b = di.bookByISBN2(searchWord);
		return new ModelAndView("SearchForBook2","b",b);
		
		}catch (Exception e) {
			System.out.println(searchWord);

			try {
		List<Book> list = di.bookByCategory(searchWord);
		List<Book> list2 = di.bookByTitle(searchWord);
				
		if(!list.isEmpty()){
			return new ModelAndView("SearchForBook","list",list);
		}
		else if(!list2.isEmpty()) {
			return new ModelAndView("SearchForBook","list",list2);
		}else {

			List<Book> list_3 = di.bookByAuthorName(searchWord);
			List<Book> list_4 = di.bookByPublisherName(searchWord);

			if(!list_3.isEmpty()) {
				return new ModelAndView("SearchForBook","list",list_3);

			}else if(!list_4.isEmpty()) {
				return new ModelAndView("SearchForBook","list",list_4);

			}
			else {return new ModelAndView("ErrorPage");}
		}
			} catch (Exception e2) {
				return new ModelAndView("ErrorPage");
		}
	}

}
	
	@RequestMapping(value = "/editBook/{id}")
	public ModelAndView editBook(@PathVariable int id) {
			Book b = di.bookByISBN(id);
			return new ModelAndView("EditBook","command",b);
	}

	@RequestMapping(value = "/udBook", method = RequestMethod.POST)
	public ModelAndView editSave(@ModelAttribute("b")Book b) {
		di.updateBook(b); 	
		return new ModelAndView("redirect:/viewBooks");
	}
		
	@RequestMapping(value = "/viewBooks")
	public ModelAndView viewBooks() {
		List<Book> list = di.viewAll();
		return new ModelAndView("ViewBooks", "list",list);	
	}
		
	
	@RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
	public ModelAndView editsave(@PathVariable int id) {
		di.deleteBook(id); 	
		return new ModelAndView("redirect:/viewBooks");
	}
	
	@RequestMapping(value = "/promoteUserAction/{id}" ,method = RequestMethod.GET)
	public ModelAndView promoteUser(@PathVariable int id) {
			Boolean b = di.promoteUserToAdmin(id);
			if(!b)
			return new ModelAndView("redirect:/sucesspromotion");
			else
			return new ModelAndView("ErrorPage");
	}
	
	@RequestMapping(value = "/sucesspromotion" ,method = RequestMethod.GET)
	public ModelAndView successPromotion() {

		return new ModelAndView("success");
		
}
	
	@RequestMapping(value = "/promoteUser/{id}" ,method = RequestMethod.GET)
	public ModelAndView promoteUserShow(@PathVariable int id) {
			Users b = di.getUserById(id);
			System.out.println(b.getEmail());
			
			return new ModelAndView("ShowUser","b",b);
	}
	
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView reports() {

		return new ModelAndView("GenerateReports");	
	}
	
	
	public List<Map<String, Object>> bookReportMethod() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Book b : di.viewAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", b.getISBN());
			item.put("name", b.getTitle());
			item.put("price", b.getPrice());
			item.put("quantity", b.getStock());
			item.put("categoryName", b.getCategory());
			result.add(item);
		}
		return result;
	}
	

	// JASPER FUNCTIONALITY
	public List<Map<String, Object>> sellingReportMethod() {
		List<Map<String, Object>> result = di.viewSellingReport();
		return result;
	}
	
	public List<Map<String, Object>> topCustomerMethod() {
		List<Map<String, Object>> result = di.topCustomerReport();
		return result;
	}
	
	public List<Map<String, Object>> totalSalesMethod() {
		List<Map<String, Object>> result = di.totalSalesReport();
		return result;
	}
	
	@RequestMapping(value = "/bookReport", method = RequestMethod.GET)
	public void bookReport(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookReportMethod());
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}
		
	@RequestMapping(value = "/sellingReport", method = RequestMethod.GET)
	public void sellingReport(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(sellingReportMethod());
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/bestSellingBooks.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}
	
	@RequestMapping(value = "/customerReport", method = RequestMethod.GET)
	public void topCustomersReport(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(topCustomerMethod());
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/topCustomers.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();

	}
	
	@RequestMapping(value = "/salesReport", method = RequestMethod.GET)
	public void totalSalesReport(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(totalSalesMethod());
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/totalSales.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();

	}
		
	// END OF JASPER FUNCTIONALITY

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView openLoginPage() {
		ModelAndView model=new ModelAndView("login");
		model.addObject("command", new Person());
		return model;	}
	
	@RequestMapping(value="/loginbuttonisclicked",method=RequestMethod.POST)
	public ModelAndView clickLoginButton(@ModelAttribute("p")Person p) {
		/*user name and email doesn't exist*/
		if(!di.isUserNameAndEmailExists(p.getName(),p.getEmail())) {
			return new ModelAndView("redirect:/viewregisrationpage");
		}
		
		return new ModelAndView("redirect:/viewhomewithoutlogging");
	}
	
	/*this method is used to open the registration page*/
	@RequestMapping(value="/viewregisrationpage",method=RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register","command",new Person());
	}
	
	/*this method is used to open the home page after logging*/
	@RequestMapping(value="/viewhomewithoutlogging",method=RequestMethod.GET)
	public ModelAndView openHomeAfterLogging() {
		return new ModelAndView("homeafterlogging","command",new Person());
	}
	
	/*this method is used to open the register validation page*/
	@RequestMapping(value="/viewregistervalidationpage",method=RequestMethod.GET)
	public ModelAndView openRegisterValidationPage() {
		return new ModelAndView("registerValidation","command",new Person());
	}
	
	/*this method is used to store the registered data in database
	 * and to redirect the user to the login page*/
	@RequestMapping(value="/clickregistrationbutton",method=RequestMethod.POST)
	public ModelAndView submitRegistration(@ModelAttribute("p")Person p){	
			/*check on the uniqueness of the user name*/
		if(di.isUserNameExists(p.getName())) {
			return new ModelAndView("redirect:/viewregistervalidationpage");
		}

		/*insert*/
      boolean insert = di.insertInToDatabase(p);
      System.out.println(insert);
	  return new ModelAndView("redirect:/login");
	}
	
}
