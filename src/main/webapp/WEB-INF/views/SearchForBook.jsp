<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	
	<style type="text/css">
		table{
		  font-family:'Calibri';
		  font-size:15px;
		  background-color:#fff;
		  color:#333;
		}
		.modal-header{
		    background-color:#333;
		    color:#fff;
		}
  	</style>
  	
  	<script>
  	$(document).ready(function(){
  		$("#mytable #checkall").click(function () {
  		        if ($("#mytable #checkall").is(':checked')) {
  		            $("#mytable input[type=checkbox]").each(function () {
  		                $(this).prop("checked", true);
  		            });

  		        } else {
  		            $("#mytable input[type=checkbox]").each(function () {
  		                $(this).prop("checked", false);
  		            });
  		        }
  		    });
  		    
  		    $("[data-toggle=tooltip]").tooltip();
  		});

  	</script>
    </head>
    <body>
<div class="container">
	<div class="row">
		<table class="table table-hover table-responsive">
		    <thead>
		        <tr>
		            <th>ISBN</th>
		            <th>Title</th>
		            <th>PID</th>
		            <th>Year</th>
		            <th>Price</th>
		            <th>Category</th>
   		            <th>Threshold</th>
   		            <th>Stock</th>
   		            <th>Edit</th>
   		            <th>Delete</th>
		        </tr>
		    </thead>
		    <tbody>
		<c:forEach var='p' items='${list}'>
	        <tr>
	        <td>${p.ISBN}</td>
	        <td>${p.title}</td>
	        <td>${p.PID}</td>
	        <td>${p.year}</td>
	        <td>${p.price}</td>
	        <td>${p.category}</td>
	        <td>${p.threshold}</td>
	        <td>${p.stock}</td>
	        <td><button type="button" data-toggle="modal" data-target="#edit" data-uid="1" class="update btn btn-warning btn-sm"
	         onclick="location.href='/project/editBook/${p.ISBN}'"><span class="glyphicon glyphicon-pencil"></span></button></td>
	       	 <td><button type="button" data-toggle="modal" data-target="#delete" data-uid="1" class="delete btn btn-danger btn-sm"
	         onclick="location.href='/project/deleteBook/${p.ISBN}'"><span class="glyphicon glyphicon-trash"></span></button></td>  
	        </tr>
        </c:forEach>
		    </tbody>
		</table>
	</div>
</div>

<div id="edit" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">Update Data</h4>
      </div>
      <div class="modal-body">
           <input id="fn" type="text" class="form-control" name="fname" placeholder="First Name">
           <input id="ln" type="text" class="form-control" name="fname" placeholder="Last Name">
           <input id="mn" type="text" class="form-control" name="fname" placeholder="Middle Name">
      </div>
      <div class="modal-footer">
        <button type="button" id="up" class="btn btn-warning" data-dismiss="modal">Update</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<div id="delete" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h4 class="modal-title">Delete Data</h4>
      </div>
      <div class="modal-body">
        <strong>Are you sure you want to delete this data?</strong>
      </div>
      <div class="modal-footer">
        <button type="button" id="del" class="btn btn-danger" data-dismiss="modal">Delete</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
    </body>
</html>



