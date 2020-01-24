<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Szczegóły oferty kredytu gotówkowego</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/headeruser.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3><img src="<c:url value="/img/${loan.bank.logo}" />" width="150" height="70" alt="${loan.bank.bankName}"/> - Szczegóły oferty ${loan.offer}</h3>

    <table class="table">
        <tr><td>Bank: </td><td style="font-weight: bold; color: crimson">${loan.bank.bankName}</td></tr>
        <tr><td>Oferta: </td><td style="font-weight: bold; color: crimson">${loan.offer}</td></tr>
        <tr><td>Kwota: </td><td style="font-weight: bold; color: crimson">${userLoan.amount} zł</td></tr>
        <tr><td>Okres: </td><td style="font-weight: bold; color: crimson">${userLoan.creditPeriod} mies.</td></tr>
        <tr><td>Rata: </td><td style="font-weight: bold; color: crimson">${payment} zł</td></tr>
        <tr><td>Oprocentowanie: </td><td style="font-weight: bold; color: crimson">${loan.creditRate}%</td></tr>
        <tr><td>Prowizja: </td><td style="font-weight: bold; color: crimson">${loan.serviceCharge}%, czyli ${serviceCharge} zł</td></tr>
        <tr><td>Ubezpieczenie: </td><td style="font-weight: bold; color: crimson">${loan.insurance}%, czyli ${insurance} zł</td></tr>
        <tr><td>Odsetki: </td><td style="font-weight: bold; color: crimson">${interests} zł</td></tr>
        <tr><td>Koszt całkowity: </td><td style="font-weight: bold; color: crimson">${totalCost} zł</td></tr>
        <tr><td></td><td><a href="${pageContext.request.contextPath}/listOfAgencies?bankId=${loan.bank.id}&amount=${userLoan.amount}&creditPeriod=${userLoan.creditPeriod}" class="btn btn-sm btn-outline-success rounded">Wybierz oddział do kontaktu</a></td></tr>
        <tr><td></td><td>
            <form action="${pageContext.request.contextPath}/generateOffer" method="post" modelAttribute="userLoan">
                <input type="hidden" name="loanId" value="${loan.id}">
                <input type="hidden" name="amount" value="${userLoan.amount}">
                <input type="hidden" name="creditPeriod" value="${userLoan.creditPeriod}">
                <input type="hidden" name="age" value="${userLoan.age}">
                <input type="hidden" name="chooseServiceCharge" value="${userLoan.chooseServiceCharge}">
                <input type="hidden" name="chooseInsurance" value="${userLoan.chooseInsurance}">
            <input type="submit" value="Wydruk do PDF" class="btn btn-sm btn-outline-success rounded">
            </form>
            </td></tr>
        <tr><td></td><td>
            <form action="${pageContext.request.contextPath}/loanParameters" method="post" modelattribute="userLoan">
                <input type="hidden" name="amount" value="${userLoan.amount}" />
                <input type="hidden" name="creditPeriod" value="${userLoan.creditPeriod}" />
                <input type="hidden" name="age" value="${userLoan.age}" />
                <input type="hidden" name="chooseServiceCharge" value="${userLoan.chooseServiceCharge}" />
                <input type="hidden" name="chooseInsurance" value="${userLoan.chooseInsurance}" />
                <p><input type="submit" value="Powrót" class="btn btn-sm btn-primary rounded" /></p>
            </form>
        </td></tr>
    </table>
</div>
</body>
</html>
