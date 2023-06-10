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
            </select>
        </div>
        <div class="form-group">
            <label for="range">Range</label>
            <select id="range" name="range" class="form-control" required>
                <option value="">Select Range</option>
                <option value="daily">Daily</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <h2>${range} ${action} 통계</h2>
    <c:if test="${logs != null}">
    <table class="table">
        <thead>
        <tr>
            <th>제품명</th>
            <c:choose>
                <c:when test="${action == 'register' or action == 'add'}">
                    <th>구매 가격</th>
                </c:when>
                <c:otherwise>
                    <th>판매 가격</th>
                </c:otherwise>
            </c:choose>
            <th>수량</th>
            <th>잔고 변화</th>
            <th>날짜</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="log" items="${logs}">
            <tr>
                <td>${log.product.name}</td>
                <c:choose>
                    <c:when test="${action == 'register' or action == 'add'}">
                        <td>${log.product.purchasePrice}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${log.product.price}
                    </c:otherwise>
                </c:choose>
                <td>${log.changeStock}</td>
                <td>${log.changeBalance}</td>
                <td>${log.timestamp}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
</body>
</html>
