<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<%@taglib uri='http://www.springframework.org/tags/form' prefix='form'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="container">
<div class="jumbotron">
<form:form method='post' action='addperson' class='form-group'>
<table class=table-bordered width="100%">
<form action="/action_page.php">
<tr>
<td>User name:</td>
<td><input path='name' class='form-control' required></td>
</tr>
<tr>
<td>First name:</td>
<td><input path='fname' class='form-control' required></td>
</tr>
<tr>
<td>Last name:</td>
<td><input path='lname' class='form-control' required></td>
</tr>
<tr>
<td>Password:</td>
<td><input path='password' class='form-control' required></td>
</tr>
<tr>
<td>Email:</td>
<td><input path='email' class='form-control' required></td>
</tr>
<tr>
<td>Phone number:</td>
<td><input path='phoneNumber' class='form-control' required></td>
</tr>
<tr>
<td>Shipping address:</td>
<td><input path='shippingAddress' class='form-control' required></td>
</tr>

<tr>
 <td><input type="radio" name="isManager" value=1 > Manager</td>
 <td><input type="radio" name="isManager" value=0 checked> Customer</td>
</tr>
<tr>
<td colspan='2'><input type='submit' value='Sign up' class='form-control'/></td>
</tr>
</form>
</table>
</form:form>
</div>
</div>