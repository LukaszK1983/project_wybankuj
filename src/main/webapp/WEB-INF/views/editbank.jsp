<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edycja banku</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/header.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3>Edycja ${bank.get().bankName}</h3>

    <table class="table">
        <form:form method="post" modelAttribute="bank">
            <form:hidden path="id" />
            <tr><td>Nazwa: </td><td><form:input path="bankName" /></td>
                <td><form:errors path="bankName" style="color: red" /></td></tr>
            <tr><td>Logo: </td><td><form:input path="logo" /></td><td></td></tr>
            <tr><td></td><td><input type="submit" value="ZAPISZ" class="btn btn-sm btn-outline-success rounded" /></td><td></td></tr>
            <input type="hidden" name="id" value="${bank.get().id}" />
        </form:form>
    </table>
</div>
</body>
</html>
