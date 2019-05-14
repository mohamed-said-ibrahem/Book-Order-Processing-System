<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
	<h3>Choose what report you want to generate?</h3>
	<a href="bookReport" target="_blank">Generate Book Report</a><br>
	<a href="sellingReport" target="_blank">Generate Best Selling Books Report</a><br>
	<a href="customerReport" target="_blank">Generate Top Customers Report</a><br>
	<a href="salesReport" target="_blank">Generate Total Sales Report</a><br>


	
    </body>
</html>