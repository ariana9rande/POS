<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <h1>상품 등록 완료</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h2>상품 목록</h2>
    <table class="table">
        <thead>
        <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>수량</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.stock}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>