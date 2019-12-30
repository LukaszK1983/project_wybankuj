<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logowanie</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron text-center border-bottom">
    <h2>Wybankuj.pl - CRM</h2>

</div>

<div class="mx-auto" style="width: 200px">
    <form method="post">
        <table cellpadding="10" class="table-light border rounded shadow p-4 mb-4 bg-white">
            <tr><td><span class="align-middle"> Login: </span></td><td><input type="text" name="username"/></td></tr>
            <tr><td><span class="align-middle"> Hasło: </span></td><td><input type="password" name="password"/></td></tr>
            <tr><td></td><td><div class="mx-auto" style="width: 100px"><input type="submit" value="ZALOGUJ" class="btn btn-outline-primary rounded" /></div></td></tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </table>
    </form>
</div>
</body>
</html>
