<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>회원 가입</title>
</head>
<body>
<h1>회원 가입</h1>
<form action="/member/signup" method="post">
    <label for="name">이름:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="role">역할:</label>
    <input type="text" id="role" name="role" required><br>

    <input type="submit" value="가입하기">
</form>
</body>
</html>
