<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 16/12/2019
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Wprowadzanie danych do symulacji</title>
</head>
<body>
    <h3>Wprowadzanie danych do symulacji kredytu hipotecznego</h3>

    <form action="${pageContext.request.contextPath}/mortgageParameters" method="post">
        <table cellpadding="10">
            <tr><td>Kwota: </td><td><input type="text" name="amount" /></td></tr>
            <tr><td>Okres: </td><td><input type="text" name="creditPeriod" /></td></tr>
            <tr><td>Wkład własny[%]: </td><td><input type="text" name="contributionPercent" /></td></tr>
            <tr><td>Prowizja: </td><td><select name="chooseServiceCharge">
                                            <option value="yes">TAK</option>
                                            <option value="no">NIE</option>
                                        </select></td></tr>
            <tr><td>Ubezpieczenie: </td><td><select name="chooseInsurance">
                                                <option value="yes">TAK</option>
                                                <option value="no">NIE</option>
                                            </select></td></tr>
            <tr><td>Wiek: </td><td><input type="text" name="age" /></td></tr>
            <tr><td></td><td><input type="submit" value="DALEJ"></td></tr>
        </table>
    </form>
</body>
</html>
