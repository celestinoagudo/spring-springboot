<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Registration Form</title>
</head>
<body>
	<form:form action="processForm" modelAttribute="student">
		<b>First Name:</b>
		<form:input path="firstName" />
		<br />
		<b>Last Name:</b>
		<form:input path="lastName" />
		<br />
		<br />
		<b>Country:</b>
		<form:select path="country">
			<form:options items="${theCountryOptions}" />
		</form:select>
		<br />
		<br />
		<b>Favorite Programming Language:</b>

		<form:radiobuttons path="favoriteLanguage"
			items="${student.favoriteLanguageOptions}" />

		<br />
		<br />
		<b>Operating Systems:</b>
		<form:checkbox path="operatingSystems" value="Linux" /> Linux
		<form:checkbox path="operatingSystems" value="Mac OS" /> Mac OS
		<form:checkbox path="operatingSystems" value="MS Windows" /> MS Windows
		<br />
		<br />


		<input type="submit" value="Submit" />
	</form:form>
</body>

</html>