<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>판매 완료</title>
</head>
<body>
<div class="header">
    <h1>판매 완료</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <div>
        <h3>판매 완료</h3>
        <c:forEach var="saleItem" items="${sale.saleItems}">
            <p>${saleItem.product.name} ${saleItem.quantity}개</p>
        </c:forEach>
        <br>
        <h4>남은 개수</h4>
        <c:forEach var="saleItem" items="${sale.saleItems}">
            <p>${saleItem.product.name} ${saleItem.product.stock}개</p>
        </c:forEach>
    </div>
</div>
</body>
</html>
