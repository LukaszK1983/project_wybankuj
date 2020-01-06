$(document).ready(function () {
    // mortgageparameters.jsp
    const button = $('button');

    function check() {
        const form = $('form');
        let testCost = true;
        let testAmount = true;
        let testCreditPeriod = true;
        let testAge = true;

        const costElement = $('#formcost');
        const cost = costElement.val();
        const regexCost = new RegExp('^\\d+$');
        const validcost = $('#validcost').hide();
        const validcostbyamount = $('#validcostbyamount').hide();

        if (!regexCost.test(cost) || cost <= 0) {
            validcost.show();
            costElement.css('border', '1px solid #C24754');
            costElement.css('box-shadow', '0 0 10px 1px #C24754');
            testCost = false;
        } else if (cost < 11200) {
            validcostbyamount.text('Minimalna kwota inwestycji to 11200 zł');
            validcostbyamount.show();
            costElement.css('border', '1px solid #C24754');
            costElement.css('box-shadow', '0 0 10px 1px #C24754');
            testCost = false;
        }
        else {
            costElement.css('border', '1px solid green');
            costElement.css('box-shadow', '0 0 0 0');
            testCost = true;
        }

        const amountElement = $('#formamount');
        const amount = amountElement.val();
        const regexAmount = new RegExp('^\\d{4,8}$');
        const validamount = $('#validamount').hide();
        const validamountbycontribution = $('#validamountbycontribution').hide();

        if (!regexAmount.test(amount) || amount < 1000 || amount > 5000000) {
            validamount.show();
            amountElement.css('border', '1px solid #C24754');
            amountElement.css('box-shadow', '0 0 10px 1px #C24754');
            testAmount = false;
        } else if ((cost - amount) / cost * 100 < 10) {
            const availableAmount = cost - cost * 0.1;
            validamountbycontribution.text("Uwzględniając wymagany wkład własny, maksymalna dostępna kwota" +
                " kredytu to " + availableAmount);
            validamountbycontribution.show();
            amountElement.css('border', '1px solid #C24754');
            amountElement.css('box-shadow', '0 0 10px 1px #C24754');
            testAmount = false;
        }
        else {
            amountElement.css('border', '1px solid green');
            amountElement.css('box-shadow', '0 0 0 0');
            testAmount = true;
        }

        const creditperiodElement = $('#formcreditperiod');
        const creditperiod = creditperiodElement.val();
        const regexCreditPeriod = new RegExp('^\\d{1,3}$');
        const validcreditperiod = $('#validcreditperiod').hide();

        if (!regexCreditPeriod.test(creditperiod) || creditperiod < 6 || creditperiod > 360) {
            validcreditperiod.show();
            creditperiodElement.css('border', '1px solid #C24754');
            creditperiodElement.css('box-shadow', '0 0 10px 1px #C24754');
            testCreditPeriod = false;
        } else {
            creditperiodElement.css('border', '1px solid green');
            creditperiodElement.css('box-shadow', '0 0 0 0');
            testCreditPeriod = true;
        }

        const ageElement = $('#formage');
        const age = ageElement.val();
        const regexAge = new RegExp('^\\d{1,3}$');
        const validage = $('#validage').hide();

        if (!regexAge.test(age)) {
            validage.show();
            ageElement.css('border', '1px solid #C24754');
            ageElement.css('box-shadow', '0 0 10px 1px #C24754');
            validage.text('Wprowadzono błędne dane');
            testAge = false;
        } else if (age < 18 || age >= 80) {
            validage.show();
            ageElement.css('border', '1px solid #C24754');
            ageElement.css('box-shadow', '0 0 10px 1px #C24754');
            validage.text('Niestety, Twój wiek uniemożliwia zaciągnięcie kredytu');
            testAge = false;
        } else {
            ageElement.css('border', '1px solid green');
            ageElement.css('box-shadow', '0 0 0 0');
            testAge = true;
        }

        const contributionElement = $('#formcontribution');
        contributionElement.val((cost - amount) / cost * 100);

        if (testCost === true && testAmount === true && testCreditPeriod === true && testAge === true) {
            form.submit();
        }
    }
    button.on('click', function (e) {
        e.preventDefault();
        check();
    });

    // --->
});