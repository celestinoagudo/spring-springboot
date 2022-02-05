<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.luv2code.springdemo.util.SortConstants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Customers</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">

			<!-- construct a sort link for first name -->
			<C:url var="sortByFirstName" value="/customer/list">
				<C:param name="sortingCriterion"
					value="<%=Integer.toString(SortConstants.FIRST_NAME)%>" />
			</C:url>

			<!-- construct a sort link for last name -->
			<C:url var="sortByLastName" value="/customer/list">
				<C:param name="sortingCriterion"
					value="<%=Integer.toString(SortConstants.LAST_NAME)%>" />
			</C:url>

			<!-- construct a sort link for email -->
			<C:url var="sortByEmail" value="/customer/list">
				<C:param name="sortingCriterion"
					value="<%=Integer.toString(SortConstants.EMAIL)%>" />
			</C:url>

			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<!--  add a search box -->
			<form:form action="search" method="GET">
                Search customer: <input type="text" name="name" />
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			<!-- add out html table here -->
			<table>
				<tr>
					<th><a href="${sortByFirstName}">First Name</a></th>
					<th><a href="${sortByLastName}">Last Name</a></th>
					<th><a href="${sortByEmail}">Email</a></th>
					<th>Action</th>
				</tr>
				<!-- loop over and print the customers -->
				<C:forEach var="customer" items="${customers}">
					<!-- construct an update link with the customer id -->
					<C:url var="updateLink" value="/customer/showFormForUpdate">
						<C:param name="customerId" value="${customer.id}"></C:param>
					</C:url>

					<C:url var="deleteLink" value="/customer/delete">
						<C:param name="customerId" value="${customer.id}"></C:param>
					</C:url>
					<tr>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.emailAddress}</td>
						<!-- display the update link -->
						<td><a href="${updateLink}">Update</a> | <a
							href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a></td>
					</tr>
				</C:forEach>
			</table>
		</div>
	</div>
</body>
</html>