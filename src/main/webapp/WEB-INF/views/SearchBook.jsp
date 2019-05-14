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

  	</style>
  	
    </head>
    <body>
  <form:form class="form-inline md-form mr-auto mb-4" method="POST" action="/project/searchBook" >
  <form:input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" path="value" required = "required"/>
  <button type="submit" data-toggle="modal" data-target="#edit" data-uid="1" class="update btn btn-warning btn-sm">Search</button>
  </form:form>
    </body>
</html>



