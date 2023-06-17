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
<%@ include file="logout.jsp" %>
<div class="header">
    <h1>상품 등록 완료</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h1>${product.name} 등록 완료</h1>
    <h3>가격 : ${product.price} 개수 : ${product.stock}</h3>
</div>
</body>
</html>
