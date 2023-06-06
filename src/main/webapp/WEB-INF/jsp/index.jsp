<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            text-align: center;
        }
        h1 {
            margin-bottom: 30px;
            color: #333;
        }
        p {
            margin-bottom: 20px;
            color: #777;
        }
        .btn-group {
            margin-top: 30px;
        }
        .btn {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Our Website</h1>
    <p>Current Date and Time: <%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %></p>
    <div class="btn-group">
        <a href="/member/join" class="btn btn-primary">회원 가입</a>
        <a href="/member/login" class="btn btn-primary">로그인</a>
    </div>
</div>
</body>
</html>
