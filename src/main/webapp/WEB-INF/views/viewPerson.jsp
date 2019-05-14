<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<%@taglib uri='http://www.springframework.org/tags/form' prefix='form'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="container">
<div class="jumbotron">
<table border='1' width='100%' cellpadding='1' class='table-bordered'>
<tr><th>User name</th><th>first name</th><th>last name</th><th>password</th><th>email</th><th>phone number</th><th>Address</th></tr>
<c:forEach var='p' items='${list}'>
<tr>
<td>${p.name}</td>
<td>${p.fname}</td>
<td>${p.lname}</td>
<td>${p.password}</td>
<td>${p.email}</td>
<td>${p.phoneNumber}</td>
<td>${p.shippingAddress}</td>
</tr>
</c:forEach>
</table>
</div>
</div>