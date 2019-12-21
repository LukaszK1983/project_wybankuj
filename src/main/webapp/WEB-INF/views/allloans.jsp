<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 11/12/2019
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Oferty kredytów gotówkowych</title>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/bank">Banki</a></p>
    <table cellpadding="10">
        <tr bgcolor="#f5f5dc"><td>Nazwa</td><td>Oprocentowanie</td><td>Prowizja</td><td>Ubezpieczenie</td><td>Min kwota</td><td>Max kwota</td><td>Min wiek</td><td>Max wiek</td><td>Max okres</td><td>Akcje</td></tr>
        <c:forEach var="loan" items="${loans}">
            <tr><td>${loan.offer}</td><td>${loan.creditRate}</td><td>${loan.serviceCharge}</td><td>${loan.insurance}</td><td>${loan.minCreditAmount}</td><td>${loan.maxCreditAmount}</td><td>${loan.minBorrowerAge}</td><td>${loan.maxBorrowerAge}</td><td>${loan.maxCreditPeriod}</td>
            <td><a href="${pageContext.request.contextPath}/loan/edit?id=${loan.id}">Edycja</a>
                <a href="${pageContext.request.contextPath}/loan/confirm?id=${loan.id}&bankId=${loan.bank.id}">Usuń</a></td></tr>
        </c:forEach>
        <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><a href="${pageContext.request.contextPath}/loan/add?bankId=${bankId}">Dodaj nową ofertę</a></td></tr>
    </table>
</body>
</html>
