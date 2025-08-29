<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h3>Edit Product</h3>
	<p style="color: red;">${errorString}</p>
	
	<form action="${pageContext.request.contextPath}/editProduct" method="post">
		<input type="hidden" name="code" value="${product.code}">
		<table>
			<tr>
				<td>Code:</td>
				<td style="color: red;"> ${product.code}</td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" value="${product.name}"></td>
			</tr>
			<tr>
				<td>Price</td>
				<td><input type="text" name="price" value="${product.price}"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Save"> 
					<a	href="${pageContext.request.contextPath}/productList">Cancel</a>
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="_footer.jsp" />
</body>
</html>