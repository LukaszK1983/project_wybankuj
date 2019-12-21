<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 18/12/2019
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły oferty kredytu hipotecznego</title>
</head>
<body>
<h3>Szczegóły oferty kredytu hipotecznego</h3>
    <table cellpadding="10">
        <tr><td>Bank: </td><td>${mortgage.bank.bankName}</td></tr>
        <tr><td>Oferta: </td><td>${mortgage.offer}</td></tr>
        <tr><td>Kwota: </td><td>${amount} zł</td></tr>
        <tr><td>Okres: </td><td>${creditPeriod}</td></tr>
        <tr><td>Rata: </td><td>${payment} zł</td></tr>
        <tr><td>Oprocentowanie: </td><td>${mortgage.creditRate}%</td></tr>
        <tr><td>Prowizja: </td><td>${mortgage.serviceCharge}%, czyli ${serviceCharge} zł</td></tr>
        <tr><td>Ubezpieczenie: </td><td>${mortgage.insurance}%, czyli ${insurance} zł</td></tr>
        <tr><td>Odsetki: </td><td>${interests} zł</td></tr>
        <tr><td>Koszt całkowity: </td><td>${totalCost} zł</td></tr>
    </table>

    <p><a href="${pageContext.request.contextPath}/listOfAgencies?bankId=${mortgage.bank.id}">Wybierz oddział do kontaktu</a></p>

    <br>

    <p><a href="${pageContext.request.contextPath}/mortgageParameters" >Nowa symulacja</a></p>
    <form action="${pageContext.request.contextPath}/mortgageParameters" method="post">
        <input type="hidden" name="amount" value="${amount}" />
        <input type="hidden" name="creditPeriod" value="${creditPeriod}" />
        <input type="hidden" name="contributionPercent" value="${contributionPercent}" />
        <input type="hidden" name="age" value="${age}" />
        <input type="hidden" name="chooseServiceCharge" value="${chooseServiceCharge}" />
        <input type="hidden" name="chooseInsurance" value="${chooseInsurance}" />
        <p><input type="submit" value="Powrót" /></p>
    </form>
</body>
</html>
