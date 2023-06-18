<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <title>판매 완료</title>
</head>
<body>
<%@ include file="logout.jsp" %>
<div class="header">
    <h1>판매 완료</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <div>
        <h3>판매 완료</h3>
        <c:forEach var="saleItem" items="${sale.saleItems}">
            <p>${saleItem.product.name} ${saleItem.quantity}개 ${saleItem.product.price * saleItem.quantity}</p>
        </c:forEach>
        <h3>총 가격 : ${sale.totalPrice}</h3>
        <br>
        <h4>남은 개수</h4>
        <c:forEach var="saleItem" items="${sale.saleItems}">
            <p>${saleItem.product.name} ${saleItem.product.stock}개</p>
        </c:forEach>
    </div>
</div>
</body>
</html>
