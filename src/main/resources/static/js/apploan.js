$(document).ready(function () {
    // loanparameters.jsp
    const button = $('button');

    function check() {
        const form = $('form');
        let testAmount;
        let testCreditPeriod;
        let testAge;

        const amountElement = $('#formamount');
        const amount = amountElement.val();
        const regexAmount = new RegExp('^\\d{4,8}$');
        const validamount = $('#validamount').hide();

        if (!regexAmount.test(amount) || amount < 1000 || amount > 200000) {
            validamount.show();
            amountElement.css('border', '1px solid #C24754');
            amountElement.css('box-shadow', '0 0 10px 1px #C24754');
            testAmount = false;
        } else {
            amountElement.css('border', '1px solid green');
            amountElement.css('box-shadow', '0 0 0 0');
            testAmount = true;
        }

        const creditperiodElement = $('#formcreditperiod');
        const creditperiod = creditperiodElement.val();
        const regexCreditPeriod = new RegExp('^\\d{1,3}$');
        const validcreditperiod = $('#validcreditperiod').hide();

        if (!regexCreditPeriod.test(creditperiod) || creditperiod < 6 || creditperiod > 120) {
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

        if (testAmount === true && testCreditPeriod === true && testAge === true) {
            form.submit();
        }
    }
    button.on('click', function (e) {
        e.preventDefault();
        check();
    });

    // --->
});