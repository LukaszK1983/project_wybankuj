<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <c:choose>
        <c:when test="${empty loanSimulation}">
            <p>Brak ofert dla wybranych parametrów. Spróbouj ponownie.</p>
            <p><a href="${pageContext.request.contextPath}/loanParameters" class="btn btn-sm btn-primary rounded">POWRÓT</a></p>
        </c:when>
        <c:otherwise>
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
                <td>${loan.key.offer}</td><td>${userLoan.amount} zł</td>
                <td>${userLoan.creditPeriod} mies.</td><td style="font-weight: bold; color: crimson">${loan.value} zł</td>
                <form action="${pageContext.request.contextPath}/loanDetails" method="post" modelAttribute="userLoan">
                    <input type="hidden" name="loanId" value="${loan.key.id}">
                    <input type="hidden" name="amount" value="${userLoan.amount}">
                    <input type="hidden" name="creditPeriod" value="${userLoan.creditPeriod}">
                    <input type="hidden" name="age" value="${userLoan.age}">
                    <input type="hidden" name="chooseServiceCharge" value="${userLoan.chooseServiceCharge}">
                    <input type="hidden" name="chooseInsurance" value="${userLoan.chooseInsurance}">
                <td><input type="submit" value="Szczegóły" class="btn btn-sm btn-outline-primary rounded"></td>
                </form>
                <td><a href="${pageContext.request.contextPath}/listOfAgencies?bankId=${loan.key.bank.id}&amount=${userLoan.amount}&creditPeriod=${userLoan.creditPeriod}" class="btn btn-sm btn-outline-primary rounded">Lista oddziałów</a></td></tr>
        </c:forEach>
    </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
