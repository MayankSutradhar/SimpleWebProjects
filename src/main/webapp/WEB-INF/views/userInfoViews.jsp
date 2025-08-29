<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info</title>
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	<h3>Hell ${user.userName}</h3>
	<br>
	User Name : ${user.userName}
	<br>
	Gender : ${user.gender}
	<jsp:include page="_footer.jsp" />
</body>
</html>