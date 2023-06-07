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
    <h1>Welcome</h1>
    <c:if test="${sessionScope.loginMember != null}">
        <div class="navbar-user">Welcome, ${sessionScope.loginMember.name}</div>
    </c:if>
</div>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">POS System</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/product/register">상품 등록</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">재고 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">통계</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <p>Current Time: <%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %>
    </p>
    <div class="btn-group">
        <a href="/member/join" class="btn btn-primary">회원 가입</a>
        <a href="/member/login" class="btn btn-primary">로그인</a>
    </div>
</div>
</body>
</html>
