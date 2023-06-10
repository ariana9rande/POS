<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Statistics - Daily</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .btn-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Statistics - Daily</h1>
</div>
<%@ include file="../navbar.jsp" %>
<div class="container" style="margin-top: 20px;">
    <div class="row justify-content-center">
        <div class="btn-group">
            <a href="/statistics/daily" class="btn btn-primary">일일 통계</a>
            <a href="/statistics/weekly" class="btn btn-primary">주간 통계</a>
            <a href="/statistics/monthly" class="btn btn-primary">월간 통계</a>
        </div>
    </div>
    <table class="table data-table">
        <thead>
        <tr>
            <th>날짜</th>
            <th>변경된 재고</th>
            <th>변경된 잔고</th>
        </tr>
        </thead>
        <tbody>
        <!-- 여기에 통계 데이터를 동적으로 추가할 수 있습니다 -->
        <c:forEach items="${dailyStatistics}" var="statistic">
            <tr>
                <td>${statistic.timestamp}</td>
                <td>${statistic.changeStock}</td>
                <td>${statistic.changeBalance}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
