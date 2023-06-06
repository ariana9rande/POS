<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head> <meta charset="utf-8"> </head>
<body>
<h1> Index Page </h1>
<%=new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ").format(new Date()) %>
</body>
</html>