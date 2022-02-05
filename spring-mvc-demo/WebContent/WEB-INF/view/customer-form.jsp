<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer Registration Form</title>
<style>
.error {
	color: red
}
</style>

</head>
<body>
	<i>Fill out the form. Asterisk (*) means required.</i>
	<form:form action="processForm" modelAttribute="customer">
		<b>First Name: </b>
		<form:input path="firstName" />
		<br />
		<b>Last Name (*): </b>
		<form:input path="lastName" />
		<form:errors path="lastName" cssClass="error" />
		<br />
		<b>Free Passes: </b>
		<form:input path="freePasses" />
		<form:errors path="freePasses" cssClass="error" />
		<br />
		<b>Postal Code: </b>
		<form:input path="postalCode" />
		<form:errors path="postalCode" cssClass="error" />
		<br />
		<b>Course Code: </b>
		<form:input path="courseCode" />
		<form:errors path="courseCode" cssClass="error" />
		<br />
		<br />
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>