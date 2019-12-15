<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 13/12/2019
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodawanie oddziału</title>
</head>
<body>
    <table cellpadding="10">
        <form:form method="post" modelAttribute="agency">
            <form:hidden path="id" />
            <tr><td>Nazwa: </td><td><form:input path="agencyName" /></td><td><form:errors path="agencyName" cssClass="error" /></td></tr>
            <tr><td>Ulica: </td><td><form:input path="street" /></td><td><form:errors path="street" cssClass="error" /></td></tr>
            <tr><td>Nr ulicy: </td><td><form:input path="streetNumber" /></td><td><form:errors path="streetNumber" cssClass="error" /></td></tr>
            <tr><td>Kod pocztowy: </td><td><form:input path="zipCode" /></td><td><form:errors path="zipCode" cssClass="error" /></td></tr>
            <tr><td>Miasto: </td><td><form:input path="city" /></td><td><form:errors path="city" cssClass="error" /></td></tr>
            <tr><td>Telefon: </td><td><form:input path="phone" /></td><td><form:errors path="phone" cssClass="error" /></td></tr>
            <tr><td>E-mail: </td><td><form:input path="email" /></td><td><form:errors path="email" cssClass="error" /></td></tr>
            <tr><td>Godziny otwarcia: </td><td><form:select path="hours" items="${hours}" /></td><td><form:errors path="hours" cssClass="error" /></td></tr>
            <tr><td>Bank: </td><td><form:select path="bank.id" items="${banks}" itemLabel="bankName" itemValue="id" /></td><td><form:errors path="bank" cssClass="error" /><td></tr>
            <tr><td></td><td><input type="submit" value="ZAPISZ"></td></tr>
        </form:form>
    </table>
</body>
</html>
