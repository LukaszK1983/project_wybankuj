<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Oddziały</title>
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
    <h3><img src="<c:url value="/img/${bank.get().logo}" />" width="150" height="70" alt="${bank.get().bankName}"/> - Oddziały</h3>

    <table class="table">
        <thead class="thead-light" style="font-size: 12px">
        <tr>
            <th>Nazwa</th>
            <th>Ulica</th>
            <th>Miasto</th>
            <th>Telefon</th>
            <th>E-mail</th>
            <th>Godziny otwarcia</th>
            <th style="text-align: center">Akcje</th>
            <th id="headaction"></th>
        </tr>
        </thead>
        <c:forEach var="agency" items="${agencies}">
            <tr  id="rows" style="font-size: 10px">
                <td>${agency.agencyName}</td>
                <td>${agency.street} ${agency.streetNumber}</td>
                <td>${agency.zipCode} ${agency.city}</td>
                <td>${agency.phone}</td>
                <td>${agency.email}</td>
                <td>${agency.hours}</td>
                <td><a href="${pageContext.request.contextPath}/agency/edit?id=${agency.id}&bankId=${agency.bank.id}" class="btn btn-sm btn-outline-primary rounded">Edycja</a>
                    <a href="${pageContext.request.contextPath}/agency/delete?id=${agency.id}&bankId=${agency.bank.id}" class="btn btn-sm btn-outline-danger rounded" id="remove">Usuń</a></td>
            </tr>
        </c:forEach>
        <tr><td></td><td></td><td></td><td></td><td></td><td></td>
            <td><a href="${pageContext.request.contextPath}/agency/add?bankId=${bank.get().id}" class="btn btn-sm btn-outline-success rounded">Dodaj nowy</a></td><td></td>
        </tr>
    </table>
</div>
</body>
</html>
