<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<link
	href="<c:url value="${contextPath}/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EXPENSES</title>
</head>
<body>
	
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<h4 class="sansserif">
		From
		<fmt:formatDate value="${from}" pattern="yy-MMM-dd" />
		till
		<fmt:formatDate value="${to}" pattern="yy-MMM-dd" />
	</h4>
	
	<div class="row">
		<form action="/deleteExpenseFromUser" method="post" class="col-md-6">
			<TABLE class="table" id="customers" BORDER="1">
			<TR>
				<TH>Expense Name</TH>
				<TH>Comments</TH>
				<TH>Date</TH>
				<TH>Amount</TH>
				<TH>Delete</TH>
				<th>Edit</th>
			</TR>

			<c:forEach items="${expense}" var="element">


				<TR>
					<TD>${element[0]}</td>
					<TD>${element[3]}</TD>
					<TD><fmt:formatDate value="${element[2]}" pattern="yy-MMM-dd" /></TD>
					<TD>${element[1]}</TD>

				
				

					<td align="center"><input type="checkbox" name="checkboxgroup"
						value="${element[4]}" /></td>
						
				     <td> <a href = "<c:url value="/edit/${element[4]}" />" >EDIT</a></td>
				</TR>
			</c:forEach>
		</TABLE>

		 <button type="submit" class="btn btn-default">Delete Expenses</button>
		</form>

	</div>
	<h3 class="sansserif">Total Expenditure :$ ${total}</h3>
	

	

</body>
</html>