<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>로그인</title>
</head>
<body>
<div class="header">
    <h1>Login</h1>
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
