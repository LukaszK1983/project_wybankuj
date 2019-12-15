<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 12/12/2019
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edycja banku</title>
</head>
<body>
    <table cellpadding="10">
        <form:form method="post" modelAttribute="bank">
            <form:hidden path="id" />
            <tr><td>Nazwa: </td><td><form:input path="bankName" /></td>
                <td><form:errors path="bankName" cssClass="error" /></td></tr>
            <tr><td>Logo: </td><td><form:input path="logo" /></td></tr>
            <tr><td></td><td><input type="submit" value="ZAPISZ" /></td></tr>
        </form:form>
    </table>
</body>
</html>
