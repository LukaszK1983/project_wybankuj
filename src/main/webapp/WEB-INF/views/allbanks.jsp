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
    <title>Banki</title>
</head>
<body>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="WYLOGUJ">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

    <table cellpadding="10">
        <tr bgcolor="#f5f5dc"><td>Logo</td><td>Nazwa</td><td>Akcje</td></tr>
        <c:forEach var="bank" items="${banks}">
            <tr><td>${bank.logo}</td><td>${bank.bankName}</td>
            <td><a href="${pageContext.request.contextPath}/agency?bankId=${bank.id}">Lista Oddziałów</a>
                <a href="${pageContext.request.contextPath}/loan?bankId=${bank.id}">Oferty gotówkowe</a>
                <a href="${pageContext.request.contextPath}/mortgage?bankId=${bank.id}">Oferty hipoteczne</a>
                <a href="${pageContext.request.contextPath}/bank/edit?id=${bank.id}">Edycja</a>
            <a href="${pageContext.request.contextPath}/bank/confirm?id=${bank.id}">Usuń</a></td></tr>
        </c:forEach>
        <tr><td></td><td></td><td><a href="${pageContext.request.contextPath}/bank/add">Dodaj nowy</a></td></tr>
    </table>
</body>
</html>
