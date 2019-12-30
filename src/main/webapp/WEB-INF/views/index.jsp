<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wybankuj</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/appindex.js"></script>
</head>
<body>
<div class="jumbotron jumbotron-fluid text-center border-bottom" style="background-color: #3D78B8; color: #FFFFFF">
    <h2>Wybankuj.pl - wszystkie kredyty w jednym miejscu</h2>

</div>

<div class="mx-auto" style="width: 600px">
    <div id="loan" class="rounded shadow p-4 mb-4" style="width: 250px; height: 200px; background-color: #19364D; border: 1px solid black; box-shadow: #3D78B8; float: left"><a href="${pageContext.request.contextPath}/loanParameters" style="text-underline: none; text-decoration-line: none; color: #FFFFFF; font-size: 25px; text-align: center"><p style="line-height: 140px; text-align: center">Kredyt gotówkowy</p></a></div>
    <div id="mortgage" class="rounded shadow p-4 mb-4" style="width: 250px; height: 200px; background-color: #19364D; border: 1px solid black; box-shadow: #3D78B8; float: right"><a href="${pageContext.request.contextPath}/mortgageParameters" style="text-underline: none; text-decoration-line: none; color: #FFFFFF; font-size: 25px; text-align: center"><p style="line-height: 140px; text-align: center">Kredyt hipoteczny</p></a></div>
</div>
</body>
</html>
