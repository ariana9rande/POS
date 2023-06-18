<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <title>회원 가입 성공</title>
</head>
<body>
<div class="header">
    <h1>회원 가입 성공</h1>
</div>
<%@ include file="../navbar.jsp" %>
<div class="container">
    <div>
        <h3>${member.name}님, 환영합니다.</h3>
    </div>
</div>
</body>
</html>
