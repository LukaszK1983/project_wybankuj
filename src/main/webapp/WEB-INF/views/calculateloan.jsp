<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Symulacje kredytów gotówkowych</title>
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
    <h3>Symulacje ofert kredytu gotówkowego</h3>

    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Bank</th>
            <th>Oferta</th>
            <th>Kwota</th>
            <th>Okres</th>
            <th>Rata</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach var="loan" items="${loanSimulation}">
            <tr>
            <td><img src="<c:url value="/img/${loan.key.bank.logo}" />" width="70" height="40" alt="${loan.key.bank.bankName}"/></td>
                <td>${loan.key.offer}</td><td>${amount} zł</td>
                <td>${creditPeriod} mies.</td><td style="font-weight: bold; color: crimson">${loan.value} zł</td>
            <td><a href="${pageContext.request.contextPath}/loanDetails?loanId=${loan.key.id}&amount=${amount}&creditPeriod=${creditPeriod}&chooseServiceCharge=${chooseServiceCharge}&chooseInsurance=${chooseInsurance}&age=${age}" class="btn btn-sm btn-outline-primary rounded">Szczegóły</a></td>
                <td><a href="${pageContext.request.contextPath}/listOfAgencies?bankId=${loan.key.bank.id}&amount=${amount}&creditperiod=${creditPeriod}" class="btn btn-sm btn-outline-primary rounded">Lista oddziałów</a></td></tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
