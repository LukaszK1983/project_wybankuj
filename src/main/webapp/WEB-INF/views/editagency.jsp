<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edycja oddziału</title>
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
    <h3>Edycja oddziału - ${bank.get().bankName}</h3>

    <table class="table">
        <form:form method="post" modelAttribute="agency">
            <form:hidden path="id" />
            <tr><td>Nazwa: </td><td><form:input path="agencyName" /></td><td><form:errors path="agencyName" style="color: red" /></td></tr>
            <tr><td>Ulica: </td><td><form:input path="street" /></td><td><form:errors path="street" style="color: red" /></td></tr>
            <tr><td>Nr ulicy: </td><td><form:input path="streetNumber" /></td><td><form:errors path="streetNumber" style="color: red" /></td></tr>
            <tr><td>Kod pocztowy: </td><td><form:input path="zipCode" /></td><td><form:errors path="zipCode" style="color: red" /></td></tr>
            <tr><td>Miasto: </td><td><form:input path="city" /></td><td><form:errors path="city" style="color: red" /></td></tr>
            <tr><td>Telefon: </td><td><form:input path="phone" /></td><td><form:errors path="phone" style="color: red" /></td></tr>
            <tr><td>E-mail: </td><td><form:input path="email" /></td><td><form:errors path="email" style="color: red" /></td></tr>
            <tr><td>Godziny otwarcia: </td><td><form:select path="hours" items="${hours}" /></td><td><form:errors path="hours" style="color: red" /></td></tr>
            <tr><td>Bank: </td><td><form:select path="bank.id" items="${banks}" itemLabel="bankName" itemValue="id" /></td><td><form:errors path="bank" style="color: red" /><td></tr>
            <form:hidden path="bank.id" value="${bank.get().id}"/>
            <input type="hidden" name="bankId" value="${bank.get().id}" />
            <tr><td></td><td><input type="submit" value="ZAPISZ" class="btn btn-sm btn-outline-success rounded"></td></tr>
        </form:form>
    </table>
</div>
</body>
</html>
