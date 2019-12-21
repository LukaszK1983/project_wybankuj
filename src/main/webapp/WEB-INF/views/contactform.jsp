<%--
  Created by IntelliJ IDEA.
  User: myszkaimisiek
  Date: 20/12/2019
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz kontaktowy</title>
</head>
<body>
    <h3>Formularz kontaktowy</h3>
    <form action="${pageContext.request.contextPath}/agencyContactForm" method="post">
        <table cellpadding="10">
            <input type="hidden" name="agencyId" value="${agency.get().id}" />
            <tr><td>Imię i nazwisko: </td><td><input type="text" name="name"></td></tr>
            <tr><td>E-mail: </td><td><input type="email" name="email"></td></tr>
            <tr><td>Telefon: </td><td><input type="text" name="phone"></td></tr>
            <tr><td>Wiadomość: </td><td><textarea rows="20" cols="30" name="message" ></textarea></td></tr>
            <tr><td></td><td><input type="submit" value="WYŚLIJ" /></td></tr>
        </table>
    </form>
</body>
</html>
