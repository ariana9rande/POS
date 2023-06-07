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
    <h1>입고 완료</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h1>${product.name} 입고 완료</h1>
    <h3>${quantity}개 추가</h3>
    <h3>현재 재고 : ${product.stock}</h3>
</div>
</body>
</html>
