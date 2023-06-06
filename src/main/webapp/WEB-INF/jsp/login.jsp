<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<form action="/member/login" method="post">
    <label for="name">이름:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="password">비밀번호:</label>
    <input type="password" id="password" name="password" required><br>

    <input type="submit" value="로그인">
</form>
</body>
</html>
