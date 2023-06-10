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
<%--    <div class="row justify-content-center">--%>
<%--        <div class="btn-group">--%>
<%--            <a href="/statistics/daily" class="btn btn-primary">일일 통계</a>--%>
<%--            <a href="/statistics/weekly" class="btn btn-primary">주간 통계</a>--%>
<%--            <a href="/statistics/monthly" class="btn btn-primary">월간 통계</a>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="statisticsDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            통계
        </button>
        <div class="dropdown-menu" aria-labelledby="statisticsDropdown">
            <a class="dropdown-item" href="/statistics/daily">일일 통계</a>
            <a class="dropdown-item" href="/statistics/weekly">주간 통계</a>
            <a class="dropdown-item" href="/statistics/monthly">월간 통계</a>
        </div>
    </div>
</div>
</body>
</html>
