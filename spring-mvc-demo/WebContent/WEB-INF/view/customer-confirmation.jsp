<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head></head>
<title>Customer Confirmation</title>
<body>
	The customer is confirmed: ${customer.firstName} ${customer.lastName}
	<br> Free Passes: ${customer.freePasses}
	<br> Postal Code: ${customer.postalCode}
	<br> Course Code: ${customer.courseCode}

</body>

</html>