<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>Home Page</title>
</head>

<body>
	<h2>Home Page</h2>
	<hr>
	<p>Welcome to the Home Page</p>

	<hr>

	<!-- display username and role -->

	<p>
		User:
		<security:authentication property="principal.username"></security:authentication>
		<br> <br> Role(s) :
		<security:authentication property="principal.authorities"></security:authentication>
	</p>
	<hr>

	<security:authorize access="hasRole('MANAGER')">
		<!-- link to /leaders for the managers -->

		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Meeting</a> (Only for Managers)
		</p>

	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<!-- link to /systems for the admins-->
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems
				Meeting</a> (Only for Admins)
		</p>

	</security:authorize>


	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />
	</form:form>

</body>
</html>