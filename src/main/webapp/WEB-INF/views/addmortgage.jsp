<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dodawanie oferty kredytu hipotecznego</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/headerfull.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3>Nowa oferta kredytu hipotecznego - ${bank.get().bankName}</h3>

    <table class="table">
    <form:form method="post" modelAttribute="mortgage">
        <tr><td>Nazwa oferty: </td><td><form:input path="offer" /></td><td><form:errors path="offer" style="color: red" /></td></tr>
        <tr><td>Oprocentowanie: </td><td><form:input path="creditRate" /></td><td><form:errors path="creditRate" style="color: red" /></td></tr>
        <tr><td>Prowizja: </td><td><form:input path="serviceCharge" /></td><td><form:errors path="serviceCharge" style="color: red" /></td></tr>
        <tr><td>Ubezpieczenie: </td><td><form:input path="insurance" /></td><td><form:errors path="insurance" style="color: red" /></td></tr>
        <tr><td>Min wkład własny: </td><td><form:input path="contributionPercent" /></td><td><form:errors path="contributionPercent" style="color: red" /></td></tr>
        <tr><td>Min kwota: </td><td><form:input path="minCreditAmount" /></td><td><form:errors path="minCreditAmount" style="color: red" /></td></tr>
        <tr><td>Max kwota: </td><td><form:input path="maxCreditAmount" /></td><td><form:errors path="maxCreditAmount" style="color: red" /></td></tr>
        <tr><td>Min wiek: </td><td><form:input path="minBorrowerAge" /></td><td><form:errors path="minBorrowerAge" style="color: red" /></td></tr>
        <tr><td>Max wiek: </td><td><form:input path="maxBorrowerAge" /></td><td><form:errors path="maxBorrowerAge" style="color: red" /></td></tr>
        <tr><td>Max okres: </td><td><form:input path="maxCreditPeriod" /></td><td><form:errors path="maxCreditPeriod" style="color: red" /></td></tr>
        <form:hidden path="bank.id" value="${bank.get().id}"/>
        <input type="hidden" name="bankId" value="${bank.get().id}" />
        <tr><td></td><td><input type="submit" value="ZAPISZ" class="btn btn-sm btn-outline-success rounded" /></td></tr>
    </form:form>
    </table>
</div>
</body>
</html>
