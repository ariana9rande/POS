<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>회원 가입 성공</title>
    <style>
        body {
            padding: 20px;
        }
        .container {
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="mt-5">회원 가입 성공</h1>
    <div>
        <h3>${member.name}님, 환영합니다.</h3>
    </div>
</div>
</body>
</html>
