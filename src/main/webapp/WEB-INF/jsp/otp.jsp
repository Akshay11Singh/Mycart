<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="/otp" method="post">
Enter OTP here:<input type="text" name="otp"  value=""/>
<input type="submit" value="Submit OTP"/>
</form>
<%= request.getAttribute("same")%>
</body>
</html>