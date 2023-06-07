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
    <h1>Product Register</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <form action="/product/add" method="post">
        <div class="form-group">
            <label for="product">Product</label>
            <select id="product" name="productId" class="form-control" required>
                <option value="">Select Product</option>
                <!-- Iterate over the list of products and generate option tags -->
                <c:forEach var="product" items="${products}">
                    <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Add Stock</button>
    </form>
</div>
</body>
</html>
