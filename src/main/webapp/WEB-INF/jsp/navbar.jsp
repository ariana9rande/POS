<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="hjh.spring.POS.domain.Balance" %>
<%@ page import="java.text.NumberFormat" %>
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
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="/statistics/">통계</a>--%>
<%--            </li>--%>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="statisticsDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    통계
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="statisticsDropdown">
                    <a class="dropdown-item dropdown-toggle" href="#" id="dailyStatisticsDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        일일 통계
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dailyStatisticsDropdown">
                        <a class="dropdown-item" href="/statistics?action=register&range=daily">Register</a>
                        <a class="dropdown-item" href="/statistics?action=add&range=daily">Add</a>
                        <a class="dropdown-item" href="/statistics?action=sell&range=daily">Sell</a>
                    </div>
                    <a class="dropdown-item dropdown-toggle" href="#" id="weeklyStatisticsDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        주간 통계
                    </a>
                    <div class="dropdown-menu" aria-labelledby="weeklyStatisticsDropdown">
                        <a class="dropdown-item" href="/statistics?action=register&range=weekly">Register</a>
                        <a class="dropdown-item" href="/statistics?action=add&range=weekly">Add</a>
                        <a class="dropdown-item" href="/statistics?action=sell&range=weekly">Sell</a>
                    </div>
                    <a class="dropdown-item dropdown-toggle" href="#" id="monthlyStatisticsDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        월간 통계
                    </a>
                    <div class="dropdown-menu" aria-labelledby="monthlyStatisticsDropdown">
                        <a class="dropdown-item" href="/statistics?action=register&range=monthly">Register</a>
                        <a class="dropdown-item" href="/statistics?action=add&range=monthly">Add</a>
                        <a class="dropdown-item" href="/statistics?action=sell&range=monthly">Sell</a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <%
        Balance balance = (Balance) session.getAttribute("balance");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String formattedBalance = numberFormat.format(balance.getAmount());

        out.println("<a class='navbar-brand' style='color:white;'> ₩" + formattedBalance + "</a>");
    %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</nav>