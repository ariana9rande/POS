<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Add</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<%@ include file="logout.jsp" %>
<div class="header">
    <h1>입고 완료</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h1>${product.name} 입고 완료</h1>
    <h3>${quantity}개 추가</h3>
    <h4>총 소요 비용 ${product.purchasePrice * quantity}</h4>
    <h4>현재 재고 : ${product.stock + quantity}</h4>
</div>
</body>
</html>
