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
    <title>Oferty kredytów hipotecznych</title>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/bank">Banki</a></p>
    <table cellpadding="10">
        <tr bgcolor="#f5f5dc"><td>Nazwa</td><td>Oprocentowanie</td><td>Prowizja</td><td>Ubezpieczenie</td><td>Min wkład własny</td><td>Min kwota</td><td>Max kwota</td><td>Min wiek</td><td>Max wiek</td><td>Max okres</td><td>Akcje</td></tr>
        <c:forEach var="mortgage" items="${mortgages}">
            <tr><td>${mortgage.offer}</td><td>${mortgage.creditRate}</td><td>${mortgage.serviceCharge}</td><td>${mortgage.insurance}</td><td>${mortgage.contributionPercent}</td><td>${mortgage.minCreditAmount}</td><td>${mortgage.maxCreditAmount}</td><td>${mortgage.minBorrowerAge}</td><td>${mortgage.maxBorrowerAge}</td><td>${mortgage.maxCreditPeriod}</td>
            <td><a href="${pageContext.request.contextPath}/mortgage/edit?id=${mortgage.id}&bankId=${mortgage.bank.id}">Edycja</a>
                <a href="${pageContext.request.contextPath}/mortgage/confirm?id=${mortgage.id}&bankId=${mortgage.bank.id}">Usuń</a></td></tr>
        </c:forEach>
        <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><a href="${pageContext.request.contextPath}/mortgage/add?bankId=${bankId}">Dodaj nową ofertę</a></td></tr>
    </table>
</body>
</html>
