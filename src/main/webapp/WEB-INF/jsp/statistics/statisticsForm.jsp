<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Statistics</title>
</head>
<body>
<div class="header">
    <h1>Statistics</h1>
</div>
<%@ include file="../navbar.jsp" %>
<div class="container" style="margin-top: 20px;">
    <form action="/statistics/statistics" method="get">
        <div class="form-group">
            <label for="action">Action</label>
            <select id="action" name="action" class="form-control" required>
                <option value="">Select Action</option>
                <option value="register">Register</option>
                <option value="add">Add</option>
                <option value="sell">Sell</option>
                <option value="all">All</option>
            </select>
        </div>
        <div class="form-group">
            <label for="range">Range</label>
            <select id="range" name="range" class="form-control" required>
                <option value="">Select Range</option>
                <option value="daily">Daily</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
                <option value="all">All</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
