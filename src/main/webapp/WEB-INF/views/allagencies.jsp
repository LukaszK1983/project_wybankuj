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
    <title>Oddziały</title>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/bank">Banki</a></p>
    <table cellpadding="10">
        <tr bgcolor="#f5f5dc"><td>Bank</td><td>Nazwa</td><td>Ulica</td><td>Miasto</td><td>Telefon</td><td>E-mail</td><td>Godziny otwarcia</td><td>Akcje</td></tr>
        <c:forEach var="agency" items="${agencies}">
            <tr><td>${agency.bank.bankName}</td><td>${agency.agencyName}</td><td>${agency.street} ${agency.streetNumber}</td>
                <td>${agency.zipCode} ${agency.city}</td><td>${agency.phone}</td>
                <td>${agency.email}</td><td>${agency.hours}</td>
            <td><a href="${pageContext.request.contextPath}/agency/edit?id=${agency.id}">Edycja</a>
            <a href="${pageContext.request.contextPath}/agency/confirm?id=${agency.id}&bankId=${agency.bank.id}">Usuń</a></td></tr>
        </c:forEach>
        <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><a href="${pageContext.request.contextPath}/agency/add?bankId=${bankId}">Dodaj nowy</a></td></tr>
    </table>
</body>
</html>
