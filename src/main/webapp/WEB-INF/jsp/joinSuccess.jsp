<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>회원 가입 성공</title>
</head>
<body>
<div class="header">
    <h1>회원 가입 성공</h1>
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
    <div>
        <h3>${member.name}님, 환영합니다.</h3>
    </div>
</div>
</body>
</html>
