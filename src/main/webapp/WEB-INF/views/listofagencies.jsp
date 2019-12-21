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
<h3>Oddziały</h3>
        <form action="${pageContext.request.contextPath}/listOfAgencies?bankId=${bank.id}" method="post">
            <p>Wyszukaj wg miasta: <input type="text" name="city" /> <input type="submit" value="SZUKAJ" /></p>
        </form>
        <c:choose>
            <c:when test="${empty agencies}">
                <p>Brak oddziału banku w wybranym mieście. Wyszukaj inną lokalizację.</p>
            </c:when>
            <c:otherwise>
    <table cellpadding="10">
        <tr bgcolor="#f5f5dc"><td>Bank</td><td>Nazwa</td><td>Ulica</td><td>Miasto</td><td>Telefon</td><td>E-mail</td><td>Godziny otwarcia</td><td></td></tr>
        <c:forEach var="agency" items="${agencies}">
            <tr><td>${agency.bank.bankName}</td><td>${agency.agencyName}</td><td>${agency.street} ${agency.streetNumber}</td>
                <td>${agency.zipCode} ${agency.city}</td><td>${agency.phone}</td>
                <td>${agency.email}</td><td>${agency.hours}</td>
            <td><a href="${pageContext.request.contextPath}/agencyContactForm?agencyId=${agency.id}">Formularz kontaktowy</a></td></tr>
        </c:forEach>
    </table>
            </c:otherwise>
        </c:choose>
</body>
</html>
