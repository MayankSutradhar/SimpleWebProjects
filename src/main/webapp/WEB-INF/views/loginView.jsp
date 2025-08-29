<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	<h3>Login Page</h3>
	<p style="color: red;">${errorString}</p>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" value="${user.userName}"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="${user.password}"></td>
			</tr>
			<tr>
				<td>Remember Me</td>
				<td><input type="checkbox" name="rememberMe" value="Y"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Login"> 
					<a	href="${pageContext.request.contextPath}/">Cancel</a>
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="_footer.jsp" />
</body>
</html>