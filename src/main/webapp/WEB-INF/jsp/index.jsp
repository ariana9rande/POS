<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Index Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="header">
    <h1>Web POS System</h1>
    <c:if test="${sessionScope.loginMember != null}">
        <div class="navbar-user">Welcome, ${sessionScope.loginMember.name}</div>
    </c:if>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <h3 id="currentTime">Current Time: </h3>
    <div class="row justify-content-center">
        <div class="btn-group">
            <a href="/member/join" class="btn btn-dark">회원 가입</a>
            <a href="/member/login" class="btn btn-dark">로그인</a>
        </div>
    </div>
</div>
<script>
    function updateCurrentTime() {
        var currentTimeElement = document.getElementById("currentTime");
        var currentTime = new Date();
        currentTimeElement.innerHTML = "Current Time: " + currentTime.toLocaleString();
    }

    // 매 초마다 현재 시간 업데이트
    setInterval(updateCurrentTime, 1000);
</script>
</body>
</html>
