<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Banki</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/header.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3>Banki</h3>

    <table class="table">
        <thead class="thead-light" style="font-size: 12px">
            <tr>
                <th>Logo</th>
                <th>Nazwa</th>
                <th style="text-align: center">Akcje</th>
                <th id="headaction"></th>
            </tr>
        </thead>
        <c:forEach var="bank" items="${banks}">
            <tr id="rows">
                <td><img src="<c:url value="/img/${bank.logo}" />" width="70" height="40" alt="${bank.bankName}"/></td>
                <td>${bank.bankName}</td>
                <td style="text-align: center"><a href="${pageContext.request.contextPath}/agency?bankId=${bank.id}" class="btn btn-sm btn-outline-primary rounded">Lista Oddziałów</a>
                <a href="${pageContext.request.contextPath}/loan?bankId=${bank.id}" class="btn btn-sm btn-outline-primary rounded">Oferty gotówkowe</a>
                <a href="${pageContext.request.contextPath}/mortgage?bankId=${bank.id}" class="btn btn-sm btn-outline-primary rounded">Oferty hipoteczne</a>
                <a href="${pageContext.request.contextPath}/bank/edit?id=${bank.id}" class="btn btn-sm btn-outline-primary rounded">Edycja</a>
                    <a href="${pageContext.request.contextPath}/bank/logo?bankId=${bank.id}" class="btn btn-sm btn-outline-primary rounded">Logo</a>
<%--                <a href="${pageContext.request.contextPath}/bank/delete?id=${bank.id}" class="btn btn-sm btn-outline-danger rounded" id="remove" onClick="if(confirm('Na pewno usunąć?') === false) return false;">Usuń</a>--%>
                    <a href="${pageContext.request.contextPath}/bank/delete?id=${bank.id}" class="btn btn-sm btn-outline-danger rounded" id="remove">Usuń</a></td>
<%--                <td id="confirm">Potwierdzasz? <a href="${pageContext.request.contextPath}/bank/delete?id=${bank.id}" class="btn btn-sm btn-outline-danger rounded">TAK</a> <span id="reject" class="btn btn-sm btn-outline-success rounded">NIE</span> </td>--%>
<%--                <td id="accept" style="display: none">Potwierdzasz? <a href="${pageContext.request.contextPath}/bank/delete?id=${bank.id}" class="btn btn-sm btn-outline-danger rounded">TAK</a> <a href="" class="btn btn-sm btn-outline-success rounded" id="reject">NIE</a></td>--%>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td style="text-align: center"><a href="${pageContext.request.contextPath}/bank/add" class="btn btn-sm btn-outline-success rounded">Dodaj nowy</a></td>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
