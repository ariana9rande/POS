<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>회원 가입</title>
</head>
<body>
<div class="header">
    <h1>Sign Up</h1>
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
    <h1 class="mt-5">회원 가입</h1>
    <form action="/member/join" method="post">
        <div class="form-group">
            <label for="email">이메일:</label>
            <input type="text" id="email" name="email" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="role">역할:</label>
            <select id="role" name="role" class="form-control" required>
                <option value="직원">직원</option>
                <option value="매니저">매니저</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">가입하기</button>
    </form>
    <div>
        ${duplicateEmail}
    </div>
</div>
</body>
</html>
