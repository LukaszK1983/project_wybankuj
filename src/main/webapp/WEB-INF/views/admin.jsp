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
<%@ include file="/WEB-INF/JSPF/headeradmin.jspf"%>

<div class="container" style="margin-top: 100px">
    <div class="mx-auto" style="width: 200px">
        <br>
        <p><a href="${pageContext.request.contextPath}/bank" class="btn btn-lg btn-outline-primary rounded">Wybankuj.pl - CRM</a></p>
    </div>
</div>
</body>
</html>
