<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link
	href="<c:url value="${contextPath}/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tracker</title>

<h2>
	<center>Expense tracker</center>
</h2>
</head>

<body>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<h3 class="sansserif">Track based on Date</h3>

	<div class="row">
		<form action="/dateTracker" method="post" class="col-md-6">

			<div class="form-group">
				<label for="exampleInputEmail1">From Date</label> <input type="date"
					class="form-control" id="exampleInputEmail1" name="fromDate"
					placeholder="Date">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Till Date</label> <input type="date"
					class="form-control" id="exampleInputEmail1" name="tillDate"
					placeholder="Date">
			</div>

			<button type="submit" class="btn btn-default">Track</button>
		</form>

	</div>


	<h3 class="sansserif">Track based on User</h3>

	<div class="row">


		<form action="/userTracker" method="post" class="col-md-6">

			<div class="form-group">
				<label>Select User</label> <select name="trackuser"
					class="form-control">
					<c:forEach items="${users}" var="databaseValue">
						<option value="${databaseValue}">${databaseValue}</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default">Track</button>

		</form>


	</div>
</body>
</html>