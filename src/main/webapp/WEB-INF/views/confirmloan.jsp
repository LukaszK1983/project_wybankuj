<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 13/12/2019
  Time: 08:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Potwierdzenie usunięcia oferty kredytu gotówkowego</title>
</head>
<body>

    <p><a href="${pageContext.request.contextPath}/loan/delete?id=${loan.get().id}&bankId=${bank.get().id}">Potwierdź usunięcie</a></p>
    <p><a href="${pageContext.request.contextPath}/loan?bankId=${bank.get().id}">Anuluj</a></p>

</body>
</html>
