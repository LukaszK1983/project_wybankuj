<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Oferty kredytów gotówkowych</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/headerfull.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3><img src="<c:url value="/img/${bank.get().logo}" />" width="150" height="70" alt="${bank.get().bankName}"/> - Kredyty gotówkowe</h3>

    <table class="table">
        <thead class="thead-light" style="font-size: 12px">
        <tr>
            <th>Nazwa</th>
            <th>Oprocentowanie</th>
            <th>Prowizja</th>
            <th>Ubezpieczenie</th>
            <th>Min kwota</th>
            <th>Max kwota</th>
            <th>Min wiek</th>
            <th>Max wiek</th>
            <th>Max okres</th>
            <th style="text-align: center">Akcje</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="loan" items="${loans}">
            <tr style="font-size: 10px">
                <td>${loan.offer}</td>
                <td>${loan.creditRate}%</td>
                <td>${loan.serviceCharge}%</td>
                <td>${loan.insurance}%</td>
                <td>${loan.minCreditAmount} zł</td>
                <td>${loan.maxCreditAmount} zł</td>
                <td>${loan.minBorrowerAge}</td>
                <td>${loan.maxBorrowerAge}</td>
                <td>${loan.maxCreditPeriod} mies.</td>
                <td><a href="${pageContext.request.contextPath}/loan/edit?id=${loan.id}&bankId=${loan.bank.id}" class="btn btn-sm btn-outline-primary rounded">Edycja</a>
                    <a href="${pageContext.request.contextPath}/loan/delete?id=${loan.id}&bankId=${loan.bank.id}" class="btn btn-sm btn-outline-danger rounded" id="remove">Usuń</a></td>
                <td></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
            <td><a href="${pageContext.request.contextPath}/loan/add?bankId=${bank.get().id}" class="btn btn-sm btn-outline-success rounded">Dodaj nową ofertę</a></td>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
