<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.loginMember != null}">
    <div class="navbar-user" style="float:right;">${sessionScope.loginMember.name}님(${sessionScope.loginMember.role})
        <a href="/member/logout">로그아웃</a>
    </div>
</c:if>