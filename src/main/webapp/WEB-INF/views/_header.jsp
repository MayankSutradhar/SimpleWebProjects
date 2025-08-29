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
	<div style="background-color: gray; height: 55px; padding: 5px">
		<div style="float: left;">
			<h1>My Site</h1>
		</div>
		<div style="float: right; text-align: right; padding: 10px;">
			Hello <b>${loginedUser.userName}</b> 
			<br> Search <input name="search" />
		</div>
	</div>
</body>
</html>