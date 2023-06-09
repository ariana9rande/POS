<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="hjh.spring.POS.domain.Balance" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">POS System</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/product/register">상품 등록</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/add">입고</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/products">상품 목록</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/sell">판매</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">통계</a>
            </li>
        </ul>
    </div>
    <%
        Balance balance = (Balance) session.getAttribute("balance");

        out.println("<a class='navbar-brand' style='color:white;'> ₩" + balance.getAmount() + "</a>");
    %>
</nav>