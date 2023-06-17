<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authorization Error</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="logout.jsp" %>
<div class="header">
    <h1>Authorization Error</h1>
</div>
<%@ include file="navbar.jsp" %>
<div class="container">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h1>Error</h1>
                <p>${errorMessage}</p>
                <div class="btn-group">
                    <a href="/member/join" class="btn btn-dark">회원 가입</a>
                    <a href="/member/login" class="btn btn-dark">로그인</a>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
