<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String uname=session.getAttribute("uname").toString();
%>
<h1>Welcome to <%= uname %> </h1>


<form action = "/dep" method ="post">
		Amount: <input type ="text" name="dep"/><br><br>
		<input type = submit value = "Deposit"/>
	</form>
<form action = "/withdrawl" method ="post">
		Amount: <input type ="text" name="wit"/><br><br>
		<input type = submit value = "Withdrawl"/>
	</form>
	
	<form action = "/check" method ="post">
		<input type = submit value = "Check Balance"/>
	</form>
</body>
</html>