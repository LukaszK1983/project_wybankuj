<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 16/12/2019
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Symulacje kredytów gotówkowych</title>
</head>
<body>
    <table cellpadding="10">
        <tr bgcolor="#f5f5dc"><td>Bank</td><td>Oferta</td><td>Kwota</td><td>Okres</td><td>Rata</td><td></td><td></td></tr>
        <c:forEach var="loan" items="${loanSimulation}">
            <tr><td>${loan.key.bank.bankName}</td><td>${loan.key.offer}</td><td>${amount}</td>
                <td>${creditPeriod}</td><td>${loan.value}</td>
            <td><a href="${pageContext.request.contextPath}/loanDetails?loanId=${loan.key.id}&amount=${amount}&creditPeriod=${creditPeriod}&chooseServiceCharge=${chooseServiceCharge}&chooseInsurance=${chooseInsurance}&age=${age}" >Szczegóły</a></td>
                <td><a href="${pageContext.request.contextPath}/listOfAgencies?bankId=${loan.key.bank.id}">Lista oddziałów</a></td></tr>
        </c:forEach>
    </table>
</body>
</html>
