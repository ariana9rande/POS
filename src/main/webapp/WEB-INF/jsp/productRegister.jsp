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
    <form action="/product/register" method="post">
        <div class="form-group">
            <label for="name">Product Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" id="price" name="price" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="stock">Quantity</label>
            <input type="number" id="stock" name="stock" class="form-control" required>
        </div>
        <div class="btn-group">
            <button type="submit" class="btn btn-primary">Register</button>
            <a href="/" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
