<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add To Sell List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <h1>Add To Sell List</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h2>Add To Sell List</h2>
    <form action="/product/addToSellList" method="post">
        <div class="form-group">
            <label for="product">Product</label>
            <select id="product" name="productId" class="form-control" required>
                <option value="">Select Product</option>
                <c:forEach var="product" items="${products}">
                    <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Add To Sell List</button>
    </form>
    <a href="/product/sellConfirm" class="btn btn-primary">판매</a>
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
                <td>${saleItem.product.price * saleItem.quantity}</td>
                <td>
<%--                    <a href="/product/removeFromSellList?saleItemId=${saleItem.id}" class="btn btn-danger">Remove</a>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table class="table">
        <tbody>
            <tr>
                <th>Total Price</th>
                <th></th>
                <th>${sale.totalPrice}</th>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>

