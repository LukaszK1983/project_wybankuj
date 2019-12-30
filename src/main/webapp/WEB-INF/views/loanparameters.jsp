<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Wprowadzanie danych do symulacji</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/apploan.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/JSPF/headeruser.jspf"%>

<div class="container"  style="margin-top: 100px">
    <h3>Dane do symulacji kredytu gotówkowego</h3>

    <form action="${pageContext.request.contextPath}/loanParameters" method="post">
        <div class="form-group">
            <label for="formamount">Kwota:</label>
            <input type="text" class="form-control" id="formamount" placeholder="Wprowadź kwotę z przedziału 1000 - 200000" name="amount">
            <div class="invalid-feedback" id="validamount">Wprowadzono błędne dane</div>
        </div>
        <div class="form-group">
            <label for="formcreditperiod">Okres:</label>
            <input type="text" class="form-control" id="formcreditperiod" placeholder="Wprowadź okres kredytu z przedziału 6 - 120" name="creditPeriod">
            <div class="invalid-feedback" id="validcreditperiod">Wprowadzono błędne dane</div>
        </div>
        <div class="form-group">
            <label for="formage">Wiek:</label>
            <input type="text" class="form-control" id="formage" placeholder="Wprowadź Twój wiek" name="age">
            <div class="invalid-feedback" id="validage"></div>
        </div>
        <div class="form-group">
            <table>
                <tr><td><label for="formservicecharge">Prowizja:</label></td>
            <td><select name="chooseServiceCharge" id="formservicecharge" class="selectpicker show-tick">
                <option value="yes">TAK</option>
                <option value="no">NIE</option>
            </select></td></tr>
                <tr><td><label for="forminsurance">Ubezpieczenie:</label></td>
                    <td><select name="chooseInsurance" id="forminsurance" class="selectpicker show-tick">
                <option value="yes">TAK</option>
                <option value="no">NIE</option>
            </select></td></tr>
            </table>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-primary rounded">DALEJ</button>
        </div>
    </form>
</div>
</body>
</html>
