<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <title>로그인</title>
</head>
<body>
<div class="header">
    <h1>Login</h1>
</div>
<%@ include file="../navbar.jsp" %>
<div class="container">
    <h1 class="mt-5">로그인</h1>
    <form action="/member/login" method="post">
        <div class="form-group">
            <label for="email">이메일:</label>
            <input type="text" id="email" name="email" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <input type="submit" value="로그인" class="btn btn-primary">
    </form>
    <div>
        ${loginFailed}
    </div>
</div>
</body>
</html>
