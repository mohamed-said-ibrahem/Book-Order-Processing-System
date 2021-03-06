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
        <h3>Welcome, Enter The Book Details</h3>
        <form:form method="POST" action="/project/addBook" >
             <table>
                <tr>
                    <td><form:label path="ISBN">ISBN</form:label></td>
                    <td><form:input path="ISBN" required = "required"/></td>
                </tr>
                <tr>
                    <td><form:label path="Title">Title</form:label></td>
                    <td><form:input path="Title" required = "required"/></td>
                </tr>
                <tr>
                    <td><form:label path="Year">Year</form:label></td>
                    <td><form:input path="Year" /></td>
                </tr>
                <tr>
                    <td><form:label path="Price">Price</form:label></td>
                    <td><form:input path="Price" required = "required"/></td>
                </tr>
                <tr>
                    <td><form:label path="Category">Category</form:label></td>
                	<td><form:select path="Category">
                      <form:option value="" label="...." />
                      <form:option value="Science" label="Science" />
                      <form:option value="Religion" label="Religion" />
                      <form:option value="Art" label="Art" />
                      <form:option value="History" label="History" />
                      <form:option value="Geography" label="Geography" />
                      </form:select></td>
                </tr>
                 <tr>
                    <td><form:label path="Threshold">Threshold</form:label></td>
                    <td><form:input path="Threshold" required = "required"/></td>
                </tr>
                <tr>
                    <td><form:label path="Stock">Stock</form:label></td>
                    <td><form:input path="Stock" required = "required" /></td>
                </tr>
                <tr>
                    <td><form:label path="publisherFname">Publisher_Fname</form:label></td>
                    <td><form:input path="publisherFname" required = "required"/></td>
                </tr>
                <tr>
                    <td><form:label path="publisherLname">Publisher_Lname</form:label></td>
                    <td><form:input path="publisherLname" required = "required"/></td>
                </tr>
				<tr>
                    <td><form:label path="publisherAddress">Publisher_Address</form:label></td>
                    <td><form:input path="publisherAddress" required = "required"/></td>
                </tr>
            	<tr>
                    <td><form:label path="publisherPhone">Publisher_Phone</form:label></td>
                    <td><form:input path="publisherPhone" required = "required"/></td>
                </tr>    
                <tr>
                    <td><form:label path="publisherEmail">Publisher_Email</form:label></td>
                    <td><form:input path="publisherEmail" required = "required"/></td>
                </tr>    
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>