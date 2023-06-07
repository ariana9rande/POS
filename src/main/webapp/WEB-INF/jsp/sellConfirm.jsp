<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sell</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <h1>Sell</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h2>Sell Items</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="saleItem" items="${sale.saleItems}">
            <tr>
                <td>${saleItem.product.name}</td>
                <td>${saleItem.quantity}</td>
                <td>
<%--                    <a href="/product/removeFromSellList?saleItemId=${saleItem.id}" class="btn btn-danger">Remove</a>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="total-price">
        <h4>Total Price: ${sale.totalPrice}</h4>
    </div>

    <form action="/product/sell" method="post">
        <button type="submit" class="btn btn-primary">Sell</button>
    </form>
</div>
</body>
</html>
