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
    <title>Potwierdzenie usunięcia oddziału</title>
</head>
<body>

    <p><a href="${pageContext.request.contextPath}/agency/delete?id=${agency.get().id}&bankId=${bank.get().id}">Potwierdź usunięcie</a></p>
    <p><a href="${pageContext.request.contextPath}/agency?bankId=${bank.get().id}">Anuluj</a></p>

</body>
</html>
