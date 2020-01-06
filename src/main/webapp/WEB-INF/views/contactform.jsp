<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formularz kontaktowy</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/appcontact.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/headeruser.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3><img src="<c:url value="/img/${agency.get().bank.logo}" />" width="150" height="70" alt="${agency.get().bank.bankName}"/> ${agency.get().agencyName} - Formularz kontaktowy</h3>

    <c:choose>
        <c:when test="${empty answear}">
        </c:when>
        <c:otherwise>
            <h6 style="color: green">Wiadomość została wysłana. Dziękujemy za kontakt.</h6>
        </c:otherwise>
    </c:choose>
    <form action="${pageContext.request.contextPath}/agencyContactForm" method="post">
        <input type="hidden" name="agencyId" value="${agency.get().id}" />
        <div class="form-group">
            <label for="formuser">Imię i nazwisko:</label>
            <input type="text" class="form-control" id="formuser" placeholder="Wprowadź imię i nazwisko" name="name">
            <div class="invalid-feedback" id="validuser">Wprowadzono błędne dane</div>
        </div>
        <div class="form-group">
            <label for="formemail">E-mail:</label>
            <input type="text" class="form-control" id="formemail" placeholder="Wprowadź e-mail" name="email">
            <div class="invalid-feedback" id="validemail">Wprowadzono błędne dane</div>
        </div>
        <div class="form-group">
            <label for="formphone">Telefon:</label>
            <input type="text" class="form-control" id="formphone" placeholder="Wprowadź nr telefonu - 9 cyfr" name="phone">
            <div class="invalid-feedback" id="validphone">Wprowadzono błędne dane</div>
        </div>
        <div class="form-group">
            <label for="formmessage">Wiadomość:</label>
            <textarea class="form-control" id="formmessage" rows="7" name="message">
                Dzień dobry,

                interesuje mnie kredyt w kwocie ${amount} zł na okres ${creditPeriod} miesięcy. Proszę o kontakt.

                Pozdrawiam
            </textarea>
        </div>
        <div class="form-group">
            <input type="hidden" name="agencyId" value="${agency.get().id}">
            <input type="hidden" name="amount" value="${amount}" />
            <input type="hidden" name="creditPeriod" value="${creditPeriod}" />
            <button type="submit" class="btn btn-sm btn-primary rounded">WYŚLIJ</button>
        </div>
    </form>
</div>
</body>
</html>
