<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%ResultSet resultset =null;%>
<html>
<head>

<link
	href="<c:url value="${contextPath}/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<h1 class="sansserif">
	
</h1>
<h2 class="sansserif">
	<center>Expense tracker</center>
</h2>
</head>
<body>
	<form:errors path="expense.*" />

	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>


	<h3 class="sansserif">Add New Expense</h3>
	<form action="/expenseSubmission" method="post"
		enctype="multipart/form-data">
		<table class="table table-striped">
			<tr>
				<td>Expense Name :</td>
				<td><input type="text" name="expenseName" /></td>
			</tr>
			<tr>
				<td>Amount :</td>
				<td><input type="text" name="amount" /></td>
			</tr>
			<tr>
				<td>Date :</td>
				<td><input type="date" name="createdDate" /></td>
			</tr>
			<tr>
				<td>Comment :</td>
				<td><input type="text" name="comments" /></td>
			</tr>



			<tr>
				<td>User : </td>
				
			<td>	<select name="user"  style="width: 150px">
						<c:forEach items="${users}" var="databaseValue">
							<option value="${databaseValue}">${databaseValue}</option>
						</c:forEach>
				</select>
				<br><br>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>



	<form action="/addUsers" method="post">
		<tr>
			<td><h3 class="sansserif">Add User :</h3></td>
			<td><input type="text" name="adduser" /> <input
				type="submit" value="Add User" /></td>
		</tr>
	</form>

	<form action="/deleteUser" method="post">
		<tr>
			<td><h3 class="sansserif">Delete User :</h3></td>
			<td><select name="deleteuser" style="width: 150px">
					<c:forEach items="${users}" var="databaseValue">
						<option value="${databaseValue}">${databaseValue}</option>
					</c:forEach>
			</select><input type="submit" value="Delete User" /></td>
		</tr>
	</form>

	<a href="${address}"> TRACK EXPENSE </a>
	</br>
	
</body>
</html>
