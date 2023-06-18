<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <title>Statistics</title>
</head>
<body>
<%@ include file="../logout.jsp" %>
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
    <br>
    <h2>${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} ${action == 'register' ? '등록' : action == 'add' ? '입고' : action == 'sell' ? '판매' : ''}
        통계</h2>
    <c:choose>
        <c:when test="${action == 'all'}">
            <br>
            <h3>${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} 등록 통계</h3>
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
                <c:set var="hasRegisterLogs" value="false"/>
                <c:forEach var="log" items="${logs}">
                    <c:if test="${log.action == 'register'}">
                        <c:set var="hasRegisterLogs" value="true"/>
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
            <h3>${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} 입고 통계</h3>
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
                <c:set var="hasAddLogs" value="false"/>
                <c:forEach var="log" items="${logs}">
                    <c:if test="${log.action == 'add'}">
                        <c:set var="hasAddLogs" value="true"/>
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
            <h3>${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} 판매 통계</h3>
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
                <c:set var="hasSellLogs" value="false"/>
                <c:forEach var="log" items="${logs}">
                    <c:if test="${log.action == 'sell'}">
                        <c:set var="hasSellLogs" value="true"/>
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
    <br><br>


    <c:choose>
        <c:when test="${action == 'all'}">
            <h3>품목별 ${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} 등록 수량</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>구매 가격</th>
                    <th>등록 수량</th>
                    <th>지출</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty statistics['register']}">
                        <c:forEach var="product" items="${statistics['register']}">
                            <tr>
                                <td>${product.key}</td>
                                <td>
                                    <c:set var="productName" value="${product.key}"/>
                                    <c:set var="purchasePrice" value="0"/>
                                    <c:forEach var="p" items="${products}">
                                        <c:if test="${p.name eq productName}">
                                            <c:set var="purchasePrice" value="${p.purchasePrice}"/>
                                        </c:if>
                                    </c:forEach>
                                        ${purchasePrice}
                                </td>
                                <td>${product.value}</td>
                                <td>${product.value * purchasePrice}</td>
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
            <h3>품목별 ${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} 입고 수량</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>구매 가격</th>
                    <th>입고 수량</th>
                    <th>지출</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty statistics['add']}">
                        <c:forEach var="product" items="${statistics['add']}">
                            <tr>
                                <td>${product.key}</td>
                                <td>
                                    <c:set var="productName" value="${product.key}"/>
                                    <c:set var="purchasePrice" value="0"/>
                                    <c:forEach var="p" items="${products}">
                                        <c:if test="${p.name eq productName}">
                                            <c:set var="purchasePrice" value="${p.purchasePrice}"/>
                                        </c:if>
                                    </c:forEach>
                                        ${purchasePrice}
                                </td>
                                <td>${product.value}</td>
                                <td>${product.value * purchasePrice}</td>
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
            <h3>품목별 ${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} 판매 수량</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>판매 가격</th>
                    <th>판매 수량</th>
                    <th>총 매출</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty statistics['sell']}">
                        <c:forEach var="product" items="${statistics['sell']}">
                            <tr>
                                <td>${product.key}</td>
                                <td>
                                    <c:set var="productName" value="${product.key}"/>
                                    <c:set var="price" value="0"/>
                                    <c:forEach var="p" items="${products}">
                                        <c:if test="${p.name eq productName}">
                                            <c:set var="price" value="${p.price}"/>
                                        </c:if>
                                    </c:forEach>
                                        ${price}
                                </td>
                                <td>${-product.value}</td>
                                <td>${-product.value * price}</td>
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
            <h3>
                품목별 ${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} ${action == 'register' ? '등록' : action == 'add' ? '입고' : '판매'}
                수량</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>제품명</th>
                    <th>${action == 'sell' ? '판매 가격' : '구매 가격'}</th>
                    <th>${action == 'register' ? '등록' : action == 'add' ? '입고' : '판매'} 수량</th>
                    <th>${action == 'register' || action == 'add' ? '지출' : '수입'}</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty statistics[action]}">
                        <c:forEach var="product" items="${statistics[action]}">
                            <c:choose>
                                <c:when test="${action eq 'register' || action eq 'add'}">
                                    <td>${product.key}</td>
                                    <td>
                                        <c:set var="productName" value="${product.key}"/>
                                        <c:set var="purchasePrice" value="0"/>
                                        <c:forEach var="p" items="${products}">
                                            <c:if test="${p.name eq productName}">
                                                <c:set var="purchasePrice" value="${p.purchasePrice}"/>
                                            </c:if>
                                        </c:forEach>
                                            ${purchasePrice}
                                    </td>
                                    <td>${product.value}</td>
                                    <td>${product.value * purchasePrice}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${product.key}</td>
                                    <td>
                                        <c:set var="productName" value="${product.key}"/>
                                        <c:set var="price" value="0"/>
                                        <c:forEach var="p" items="${products}">
                                            <c:if test="${p.name eq productName}">
                                                <c:set var="price" value="${p.price}"/>
                                            </c:if>
                                        </c:forEach>
                                            ${price}
                                    </td>
                                    <td>${-product.value}</td>
                                    <td>${-product.value * price}</td>
                                </c:otherwise>
                            </c:choose>
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


    <br><br>
    <c:set var="topProductBySales" value=""/>
    <c:set var="topProductByRevenue" value=""/>
    <c:set var="maxSales" value="0"/>
    <c:set var="maxRevenue" value="0"/>

    <c:forEach var="product" items="${statistics['sell']}">
        <c:if test="${-product.value > maxSales}">
            <c:set var="topProductBySales" value="${product.key}"/>
            <c:set var="maxSales" value="${-product.value}"/>
        </c:if>
    </c:forEach>

    <c:forEach var="product" items="${statistics['sell']}">
        <c:set var="price" value="0"/>
        <c:forEach var="p" items="${products}">
            <c:if test="${p.name eq product.key}">
                <c:set var="price" value="${p.price}"/>
            </c:if>
        </c:forEach>
        <c:set var="revenue" value="${-product.value * price}"/>
        <c:if test="${revenue > maxRevenue}">
            <c:set var="topProductByRevenue" value="${product.key}"/>
            <c:set var="maxRevenue" value="${revenue}"/>
        </c:if>
    </c:forEach>


    <h3>${range == 'daily' ? '일일' : range == 'weekly' ? '주간' : range == 'monthly' ? '월간' : '전체'} ${action == 'register' ? '등록' : action == 'add' ? '입고' : action == 'sell' ? '판매' : '총'}
        결산</h3>
    <c:if test="${action == 'sell' || action == 'all'}">
        <br>
        <h5>최다 판매 품목 : ${empty topProductBySales ? '데이터가 존재하지 않습니다.' : topProductBySales}</h5>
        <p>판매 개수 : ${empty maxSales ? '0' : maxSales}</p>
        <br>
        <h5>최고 매출 품목 : ${empty topProductByRevenue ? '데이터가 존재하지 않습니다.' : topProductByRevenue}</h5>
        <p>매출액 : ${empty maxRevenue ? '0' : maxRevenue}</p>

        <br>
    </c:if>
    <c:set var="totalRevenue" value="0"/>
    <c:set var="totalExpense" value="0"/>


    <c:if test="${not empty statistics['register']}">
        <c:forEach var="product" items="${statistics['register']}">
            <c:set var="purchasePrice" value="0"/>
            <c:forEach var="p" items="${products}">
                <c:if test="${p.name eq product.key}">
                    <c:set var="purchasePrice" value="${p.purchasePrice}"/>
                </c:if>
            </c:forEach>
            <c:set var="expense" value="${product.value * purchasePrice}"/>
            <c:set var="totalExpense" value="${totalExpense + expense}"/>
        </c:forEach>
    </c:if>
    <c:if test="${not empty statistics['add']}">
        <c:forEach var="product" items="${statistics['add']}">
            <c:set var="purchasePrice" value="0"/>
            <c:forEach var="p" items="${products}">
                <c:if test="${p.name eq product.key}">
                    <c:set var="purchasePrice" value="${p.purchasePrice}"/>
                </c:if>
            </c:forEach>
            <c:set var="expense" value="${product.value * purchasePrice}"/>
            <c:set var="totalExpense" value="${totalExpense + expense}"/>
        </c:forEach>
    </c:if>
    <c:if test="${not empty statistics['sell']}">
        <c:forEach var="product" items="${statistics['sell']}">
            <c:set var="price" value="0"/>
            <c:forEach var="p" items="${products}">
                <c:if test="${p.name eq product.key}">
                    <c:set var="price" value="${p.price}"/>
                </c:if>
            </c:forEach>
            <c:set var="revenue" value="${-product.value * price}"/>
            <c:set var="totalRevenue" value="${totalRevenue + revenue}"/>
        </c:forEach>
    </c:if>


    <c:if test="${action != 'sell'}">
        <h5>총 지출: ${totalExpense}</h5>
    </c:if>
    <c:if test="${action == 'sell' || action == 'all'}">
        <h5>총 수입: ${totalRevenue}</h5>
    </c:if>
    <c:if test="${action == 'all'}">
        <h4>합계 : ${totalRevenue - totalExpense}</h4>
    </c:if>

</div>
</body>
</html>