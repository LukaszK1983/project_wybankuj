<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 15/12/2019
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodawanie oferty kredytu gotówkowego</title>
</head>
<body>
    <table cellpadding="10">
    <form:form method="post" modelAttribute="loan">
        <tr><td>Nazwa oferty: </td><td><form:input path="offer" /></td><td><form:errors path="offer" cssClass="error" /></td></tr>
        <tr><td>Oprocentowanie: </td><td><form:input path="creditRate" /></td><td><form:errors path="creditRate" cssClass="error" /></td></tr>
        <tr><td>Prowizja: </td><td><form:input path="serviceCharge" /></td><td><form:errors path="serviceCharge" cssClass="error" /></td></tr>
        <tr><td>Ubezpieczenie: </td><td><form:input path="insurance" /></td><td><form:errors path="insurance" cssClass="error" /></td></tr>
        <tr><td>Min kwota: </td><td><form:input path="minCreditAmount" /></td><td><form:errors path="minCreditAmount" cssClass="error" /></td></tr>
        <tr><td>Max kwota: </td><td><form:input path="maxCreditAmount" /></td><td><form:errors path="maxCreditAmount" cssClass="error" /></td></tr>
        <tr><td>Min wiek: </td><td><form:input path="minBorrowerAge" /></td><td><form:errors path="minBorrowerAge" cssClass="error" /></td></tr>
        <tr><td>Max wiek: </td><td><form:input path="maxBorrowerAge" /></td><td><form:errors path="maxBorrowerAge" cssClass="error" /></td></tr>
        <tr><td>Max okres: </td><td><form:input path="maxCreditPeriod" /></td><td><form:errors path="maxCreditPeriod" cssClass="error" /></td></tr>
        <tr><td>Bank: </td><td><form:select path="bank" items="${banks}" itemLabel="bankName" itemValue="id" /></td><td><form:errors path="bank" cssClass="error" /></td></tr>
        <tr><td></td><td><input type="submit" value="ZAPISZ" /></td></tr>
    </form:form>
    </table>
</body>
</html>
