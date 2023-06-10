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
<%@ include file="../navbar.jsp" %>
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
