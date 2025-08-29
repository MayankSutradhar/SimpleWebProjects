<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="padding: 5px;">
		<a href="${pageContext.request.contextPath}/">Home</a>|
		<a href="${pageContext.request.contextPath}/productList">Product List</a>|
		<a href="${pageContext.request.contextPath}/userinfo">User Info</a>|
		<a href="${pageContext.request.contextPath}/login">Login</a>
	</div>
</body>
</html>