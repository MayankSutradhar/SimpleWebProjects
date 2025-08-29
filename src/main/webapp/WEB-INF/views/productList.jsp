<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	<h3>Product List</h3>
	<p style="color: red;">${errorString}</p>
		<table border="1">
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach items="${productList }" var="list">
				<tr>
					<td>${list.code }</td>
					<td>${list.name }</td>
					<td>${list.price }</td>
					<td><a href="editProduct?code=${list.code }">Edit</a></td>
					<td><a href="deleteProduct?code=${list.code }">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="createProduct">Create Product</a>
	<jsp:include page="_footer.jsp" />
</body>
</html>