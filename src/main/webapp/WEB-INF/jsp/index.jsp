<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        h1 {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Index Page</h1>
    <p><%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %></p>
    <a href="/member/join" class="btn btn-primary">회원 가입</a>
    <a href="/member/login" class="btn btn-primary">로그인</a>
</div>
</body>
</html>
