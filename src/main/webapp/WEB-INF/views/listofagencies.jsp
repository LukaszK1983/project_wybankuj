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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/headeruser.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3><img src="<c:url value="/img/${bank.logo}" />" width="150" height="70" alt="${bank.bankName}"/> - lista oddziałów</h3>

    <div class="float-right">
        <form action="${pageContext.request.contextPath}/listOfAgencies" method="post">
            <input type="hidden" name="bankId" value="${bank.id}" />
            <input type="hidden" name="amount" value="${amount}" />
            <input type="hidden" name="creditPeriod" value="${creditPeriod}" />
            <p>Wyszukaj wg miasta:
                <label><input type="text" name="city" /></label>
                <label><input type="submit" value="SZUKAJ" class="btn btn-sm btn-primary rounded" /></label></p>
        </form>
    </div>
    <div>
        <c:choose>
            <c:when test="${empty agencies}">
                <p style="color: red; font-weight: bold">Brak oddziału banku w wybranym mieście. Wyszukaj inną lokalizację.</p>
            </c:when>
            <c:otherwise>

        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th>Nazwa</th>
                    <th>Ulica</th>
                    <th>Miasto</th>
                    <th>Telefon</th>
                    <th>E-mail</th>
                    <th>Otwarte</th>
                    <th></th>
                </tr>
            </thead>
        <c:forEach var="agency" items="${agencies}">
            <tr style="font-size: 12px"><td>${agency.agencyName}</td><td>${agency.street} ${agency.streetNumber}</td>
                <td>${agency.zipCode} ${agency.city}</td><td>${agency.phone}</td>
                <td>${agency.email}</td><td>${agency.hours}</td>
            <td><a href="${pageContext.request.contextPath}/agencyContactForm?agencyId=${agency.id}&amount=${amount}&creditPeriod=${creditPeriod}" class="btn btn-sm btn-outline-info rounded">Formularz kontaktowy</a></td></tr>
        </c:forEach>
    </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
