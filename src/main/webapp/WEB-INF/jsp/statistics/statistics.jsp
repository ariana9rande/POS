<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Statistics</title>
</head>
<body>
<div class="header">
    <h1>Statistics</h1>
</div>
<%@ include file="../navbar.jsp" %>
<div class="container" style="margin-top: 20px;">
    <form action="/statistics/statistics" method="get">
        <div class="form-group">
            <label for="action">Action</label>
            <select id="action" name="action" class="form-control" required>
                <option value="">Select Action</option>
                <option value="register">Register</option>
                <option value="add">Add</option>
                <option value="sell">Sell</option>
                <option value="all">All</option>
            </select>
        </div>
        <div class="form-group">
            <label for="range">Range</label>
            <select id="range" name="range" class="form-control" required>
                <option value="">Select Range</option>
                <option value="daily">Daily</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
                <option value="all">All</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <h2>${range} ${action} 통계</h2>
    <c:choose>
        <c:when test="${action == 'all'}">
            <h3>${range} 등록 통계</h3>
            <table class="table" id="t1">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>구매 가격</th>
                    <th>수량</th>
                    <th>지출</th>
                    <th>날짜</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="hasRegisterLogs" value="false" />
                <c:forEach var="log" items="${logs}">
                    <c:if test="${log.action == 'register'}">
                        <c:set var="hasRegisterLogs" value="true" />
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${hasRegisterLogs == 'false'}">
                        <tr>
                            <td colspan="5">
                                <p>데이터가 존재하지 않습니다.</p>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="log" items="${logs}">
                            <c:if test="${log.action == 'register'}">
                                <tr>
                                    <td>${log.product.name}</td>
                                    <td>${log.product.purchasePrice}</td>
                                    <td>${log.changeStock}</td>
                                    <td>${-log.changeBalance}</td>
                                    <td>${log.timestamp}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <h3>${range} 입고 통계</h3>
            <table class="table" id="t2">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>구매 가격</th>
                    <th>수량</th>
                    <th>지출</th>
                    <th>날짜</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="hasAddLogs" value="false" />
                <c:forEach var="log" items="${logs}">
                    <c:if test="${log.action == 'add'}">
                        <c:set var="hasAddLogs" value="true" />
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${hasAddLogs == 'false'}">
                        <tr>
                            <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="log" items="${logs}">
                            <c:if test="${log.action == 'add'}">
                                <tr>
                                    <td>${log.product.name}</td>
                                    <td>${log.product.purchasePrice}</td>
                                    <td>${log.changeStock}</td>
                                    <td>${-log.changeBalance}</td>
                                    <td>${log.timestamp}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
            <h3>${range} 판매 통계</h3>
            <table class="table" id="t2">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>판매 가격</th>
                    <th>수량</th>
                    <th>수입</th>
                    <th>날짜</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="hasSellLogs" value="false" />
                <c:forEach var="log" items="${logs}">
                    <c:if test="${log.action == 'sell'}">
                        <c:set var="hasSellLogs" value="true" />
                    </c:if>
                </c:forEach>
                <c:choose>
                    <c:when test="${hasSellLogs == 'false'}">
                        <tr>
                            <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="log" items="${logs}">
                            <c:if test="${log.action == 'sell'}">
                                <tr>
                                    <td>${log.product.name}</td>
                                    <td>${log.product.price}</td>
                                    <td>${-log.changeStock}</td>
                                    <td>${log.changeBalance}</td>
                                    <td>${log.timestamp}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${action == 'register'}">
                    <table class="table" id="t1">
                        <thead>
                        <tr>
                            <th>제품명</th>
                            <th>구매 가격</th>
                            <th>수량</th>
                            <th>지출</th>
                            <th>날짜</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty logs}">
                                <tr>
                                    <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="log" items="${logs}">
                                    <tr>
                                        <td>${log.product.name}</td>
                                        <td>${log.product.purchasePrice}</td>
                                        <td>${log.changeStock}</td>
                                        <td>${-log.changeBalance}</td>
                                        <td>${log.timestamp}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </c:when>
                <c:when test="${action == 'add'}">
                    <table class="table" id="t1">
                        <thead>
                        <tr>
                            <th>제품명</th>
                            <th>구매 가격</th>
                            <th>수량</th>
                            <th>지출</th>
                            <th>날짜</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty logs}">
                                <tr>
                                    <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="log" items="${logs}">
                                    <tr>
                                        <td>${log.product.name}</td>
                                        <td>${log.product.purchasePrice}</td>
                                        <td>${log.changeStock}</td>
                                        <td>${-log.changeBalance}</td>
                                        <td>${log.timestamp}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <table class="table" id="t2">
                        <thead>
                        <tr>
                            <th>제품명</th>
                            <th>판매 가격</th>
                            <th>수량</th>
                            <th>수입</th>
                            <th>날짜</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty logs}">
                                <tr>
                                    <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="log" items="${logs}">
                                    <tr>
                                        <td>${log.product.name}</td>
                                        <td>${log.product.price}</td>
                                        <td>${-log.changeStock}</td>
                                        <td>${log.changeBalance}</td>
                                        <td>${log.timestamp}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>








<%--    <c:choose>--%>
<%--        <c:when test="${action == 'register' or action == 'add' or action == 'all'}">--%>
<%--            <table class="table" id="t1">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>구매 가격</th>--%>
<%--                    <th>수량</th>--%>
<%--                    <th>지출</th>--%>
<%--                    <th>날짜</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${empty groupedLogs.register and empty groupedLogs.add and empty groupedLogs.sell}">--%>
<%--                        <tr>--%>
<%--                            <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>--%>
<%--                        </tr>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <c:forEach var="log" items="${groupedLogs.register}">--%>
<%--                            <tr>--%>
<%--                                <td>${log.product.name}</td>--%>
<%--                                <td>${log.product.purchasePrice}</td>--%>
<%--                                <td>${log.changeStock}</td>--%>
<%--                                <td>${-log.changeBalance}</td>--%>
<%--                                <td>${log.timestamp}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                        <c:forEach var="log" items="${groupedLogs.add}">--%>
<%--                            <tr>--%>
<%--                                <td>${log.product.name}</td>--%>
<%--                                <td>${log.product.purchasePrice}</td>--%>
<%--                                <td>${log.changeStock}</td>--%>
<%--                                <td>${-log.changeBalance}</td>--%>
<%--                                <td>${log.timestamp}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                        <c:forEach var="log" items="${groupedLogs.sell}">--%>
<%--                            <tr>--%>
<%--                                <td>${log.product.name}</td>--%>
<%--                                <td>${log.product.price}</td>--%>
<%--                                <td>${-log.changeStock}</td>--%>
<%--                                <td>${log.changeBalance}</td>--%>
<%--                                <td>${log.timestamp}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--        <c:when test="${action == 'sell'}">--%>
<%--            <table class="table" id="t2">--%>
<%--                <!-- 테이블 헤더와 내용 -->--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>판매 가격</th>--%>
<%--                    <th>수량</th>--%>
<%--                    <th>수입</th>--%>
<%--                    <th>날짜</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${empty groupedLogs.sell}">--%>
<%--                        <tr>--%>
<%--                            <td colspan="5"><p>데이터가 존재하지 않습니다.</p></td>--%>
<%--                        </tr>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <c:forEach var="log" items="${groupedLogs.sell}">--%>
<%--                            <tr>--%>
<%--                                <td>${log.product.name}</td>--%>
<%--                                <td>${log.product.price}</td>--%>
<%--                                <td>${log.changeStock}</td>--%>
<%--                                <td>${log.changeBalance}</td>--%>
<%--                                <td>${log.timestamp}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--    </c:choose>--%>









<%--    <c:choose>--%>
<%--        <c:when test="${action == 'all'}">--%>
<%--            <h3>${range} 등록 통계</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>등록 수량</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty statistics['register']}">--%>
<%--                        <c:forEach var="product" items="${statistics['register']}">--%>
<%--                            <tr>--%>
<%--                                <td>${product.key}</td>--%>
<%--                                <td>${product.value}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <tr>--%>
<%--                            <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                        </tr>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--            <h3>품목별 입고 수량</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>입고 수량</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty statistics['add']}">--%>
<%--                        <c:forEach var="product" items="${statistics['add']}">--%>
<%--                            <tr>--%>
<%--                                <td>${product.key}</td>--%>
<%--                                <td>${product.value}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <tr>--%>
<%--                            <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                        </tr>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--            <h3>품목별 판매 수량</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>판매 수량</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty statistics['sell']}">--%>
<%--                        <c:forEach var="product" items="${statistics['sell']}">--%>
<%--                            <tr>--%>
<%--                                <td>${product.key}</td>--%>
<%--                                <td>${product.value}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <tr>--%>
<%--                            <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                        </tr>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <h3>품목별 ${action == 'register' ? '등록' : action == 'add' ? '입고' : '판매'} 수량</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>${action == 'sell' ? '판매 가격' : '구매 가격'}</th>--%>
<%--                    <th>수량</th>--%>
<%--                    <th>${action == 'register' || action == 'add' ? '지출' : '수입'}</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${not empty groupedLogs[action]}">--%>
<%--                        <c:forEach var="log" items="${groupedLogs[action]}">--%>
<%--                            <tr>--%>
<%--                                <td>${log.product.name}</td>--%>
<%--                                <td>${action == 'sell' ? log.product.price : log.product.purchasePrice}</td>--%>
<%--                                <td>${log.changeStock}</td>--%>
<%--                                <td>${action == 'register' || action == 'add' ? -log.changeBalance : log.changeBalance}</td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <tr>--%>
<%--                            <td colspan="4">데이터가 존재하지 않습니다.</td>--%>
<%--                        </tr>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>














<%--    <c:choose>--%>
<%--        <c:when test="${action == 'register'}">--%>
<%--            <h3>품목별 등록 수량</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>등록 수량</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="product" items="${statistics.register}">--%>
<%--                    <tr>--%>
<%--                        <td>${product.key}</td>--%>
<%--                        <td>${product.value}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                <c:if test="${empty statistics.register}">--%>
<%--                    <tr>--%>
<%--                        <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                    </tr>--%>
<%--                </c:if>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--        <c:when test="${action == 'add'}">--%>
<%--            <h3>품목별 입고 수량</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>입고 수량</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="product" items="${statistics.add}">--%>
<%--                    <tr>--%>
<%--                        <td>${product.key}</td>--%>
<%--                        <td>${product.value}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                <c:if test="${empty statistics.add}">--%>
<%--                    <tr>--%>
<%--                        <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                    </tr>--%>
<%--                </c:if>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--        <c:when test="${action == 'sell'}">--%>
<%--            <h3>품목별 판매 수량</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>제품명</th>--%>
<%--                    <th>판매 수량</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="product" items="${statistics.sell}">--%>
<%--                    <tr>--%>
<%--                        <td>${product.key}</td>--%>
<%--                        <td>${-product.value}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                <c:if test="${empty statistics.sell}">--%>
<%--                    <tr>--%>
<%--                        <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                    </tr>--%>
<%--                </c:if>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <c:forEach var="entry" items="${groupedLogs}">--%>
<%--                <c:set var="action" value="${entry.key}" />--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${action == 'register'}">--%>
<%--                        <h3>품목별 등록 수량</h3>--%>
<%--                        <table class="table">--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th>제품명</th>--%>
<%--                                <th>등록 수량</th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            <c:forEach var="product" items="${statistics.register}">--%>
<%--                                <tr>--%>
<%--                                    <td>${product.key}</td>--%>
<%--                                    <td>${product.value}</td>--%>
<%--                                </tr>--%>
<%--                            </c:forEach>--%>
<%--                            <c:if test="${empty statistics.register}">--%>
<%--                                <tr>--%>
<%--                                    <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                                </tr>--%>
<%--                            </c:if>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${action == 'add'}">--%>
<%--                        <h3>품목별 입고 수량</h3>--%>
<%--                        <table class="table">--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th>제품명</th>--%>
<%--                                <th>입고 수량</th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            <c:forEach var="product" items="${statistics.add}">--%>
<%--                                <tr>--%>
<%--                                    <td>${product.key}</td>--%>
<%--                                    <td>${product.value}</td>--%>
<%--                                </tr>--%>
<%--                            </c:forEach>--%>
<%--                            <c:if test="${empty statistics.add}">--%>
<%--                                <tr>--%>
<%--                                    <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                                </tr>--%>
<%--                            </c:if>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                    </c:when>--%>
<%--                    <c:when test="${action == 'sell'}">--%>
<%--                        <h3>품목별 판매 수량</h3>--%>
<%--                        <table class="table">--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th>제품명</th>--%>
<%--                                <th>판매 수량</th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            <c:forEach var="product" items="${statistics.sell}">--%>
<%--                                <tr>--%>
<%--                                    <td>${product.key}</td>--%>
<%--                                    <td>${-product.value}</td>--%>
<%--                                </tr>--%>
<%--                            </c:forEach>--%>
<%--                            <c:if test="${empty statistics.sell}">--%>
<%--                                <tr>--%>
<%--                                    <td colspan="2">데이터가 존재하지 않습니다.</td>--%>
<%--                                </tr>--%>
<%--                            </c:if>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                    </c:when>--%>
<%--                </c:choose>--%>
<%--            </c:forEach>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
    <c:choose>
        <c:when test="${action == 'register' || action == 'add' || action == 'all' || action == 'sell'}">
            <c:choose>
                <c:when test="${action == 'all'}">
                    <h3>품목별 등록 수량</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>제품명</th>
                                <%-- <th>${actionName == 'sell' ? '판매 가격' : '구매 가격'}</th> --%>
                            <th>등록 수량</th>
                                <%-- <th>${actionName == 'register' || actionName == 'add' ? '지출' : '수입'}</th> --%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty statistics['register']}">
                                <c:forEach var="product" items="${statistics['register']}">
                                    <tr>
                                        <td>${product.key}</td>
                                            <%-- <td>${actionName == 'sell' ? product.value.price : product.value.purchasePrice}</td> --%>
                                        <td>${product.value}</td>
                                            <%-- <td>${product.value.changeBalance}</td> --%>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="2">데이터가 존재하지 않습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <h3>품목별 입고 수량</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>제품명</th>
                                <%-- <th>${actionName == 'sell' ? '판매 가격' : '구매 가격'}</th> --%>
                            <th>입고 수량</th>
                                <%-- <th>${actionName == 'register' || actionName == 'add' ? '지출' : '수입'}</th> --%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty statistics['add']}">
                                <c:forEach var="product" items="${statistics['add']}">
                                    <tr>
                                        <td>${product.key}</td>
                                            <%-- <td>${actionName == 'sell' ? product.value.price : product.value.purchasePrice}</td> --%>
                                        <td>${product.value}</td>
                                            <%-- <td>${product.value.changeBalance}</td> --%>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="2">데이터가 존재하지 않습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <h3>품목별 판매 수량</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>제품명</th>
                                <%-- <th>${actionName == 'sell' ? '판매 가격' : '구매 가격'}</th> --%>
                            <th>판매 수량</th>
                                <%-- <th>${actionName == 'register' || actionName == 'add' ? '지출' : '수입'}</th> --%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty statistics['sell']}">
                                <c:forEach var="product" items="${statistics['sell']}">
                                    <tr>
                                        <td>${product.key}</td>
                                            <%-- <td>${actionName == 'sell' ? product.value.price : product.value.purchasePrice}</td> --%>
                                        <td>${-product.value}</td>
                                            <%-- <td>${product.value.changeBalance}</td> --%>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="2">데이터가 존재하지 않습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h3>품목별 ${action == 'register' ? '등록' : action == 'add' ? '입고' : '판매'} 수량</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>제품명</th>
                                <%-- <th>${actionName == 'sell' ? '판매 가격' : '구매 가격'}</th> --%>
                            <th>${action == 'register' ? '등록' : action == 'add' ? '입고' : '판매'} 수량</th>
                                <%-- <th>${actionName == 'register' || actionName == 'add' ? '지출' : '수입'}</th> --%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${not empty statistics[action]}">
                                <c:forEach var="product" items="${statistics[action]}">
                                    <tr>
                                        <td>${product.key}</td>
                                            <%-- <td>${actionName == 'sell' ? product.value.price : product.value.purchasePrice}</td> --%>
                                        <td>${action == 'register' || action == 'add' ? product.value : -product.value}</td>
                                            <%-- <td>${product.value.changeBalance}</td> --%>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="2">데이터가 존재하지 않습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
</div>
</body>
</html>
