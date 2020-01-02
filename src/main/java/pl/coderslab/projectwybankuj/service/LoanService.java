package pl.coderslab.projectwybankuj.service;

import org.springframework.stereotype.Service;
import pl.coderslab.projectwybankuj.entity.Loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class LoanService {

    public Map<Loan, BigDecimal> calculateLoanPayment(List<Loan> loans, int amount, int creditPeriod, int age) {
        Map<Loan, BigDecimal> loansWithPayments = new HashMap<>();

        for (Loan loan : loans) {
            BigDecimal rateRatio = BigDecimal.valueOf(1).add((BigDecimal.valueOf(loan.getCreditRate()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
            BigDecimal payment = BigDecimal.valueOf(amount).multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
            BigDecimal roundPayment = payment.setScale(2, RoundingMode.HALF_UP);
            loansWithPayments.put(loan, roundPayment);
        }
        loansWithPayments = sortByPayment(loansWithPayments);
        return loansWithPayments;
    }

    public BigDecimal calculateChoosenLoanPayment(Loan loan, int amount, int creditPeriod) {
        BigDecimal rateRatio = BigDecimal.valueOf(1).add((BigDecimal.valueOf(loan.getCreditRate()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
        BigDecimal payment = BigDecimal.valueOf(amount).multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
        BigDecimal roundPayment = payment.setScale(2, RoundingMode.HALF_UP);
        return roundPayment;
    }

    public BigDecimal calculateServiceCharge(Loan loan, int amount) {
        BigDecimal serviceCharge = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(loan.getServiceCharge()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));
        BigDecimal roundServiceCharge = serviceCharge.setScale(2, RoundingMode.HALF_UP);
        return roundServiceCharge;
    }

    public BigDecimal calculateInsurance(Loan loan, int amount) {
        BigDecimal insurance = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(loan.getInsurance()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));
        BigDecimal roundInsurance = insurance.setScale(2, RoundingMode.HALF_UP);
        return roundInsurance;
    }

    public BigDecimal calculateInterestsCost(Loan loan, int amount, int creditPeriod, BigDecimal payment, BigDecimal serviceCharge, BigDecimal insurance) {
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount)).subtract(serviceCharge).subtract(insurance);
        BigDecimal roundInterests = interests.setScale(2, RoundingMode.HALF_UP);
        return roundInterests;
    }

    public BigDecimal calculateTotalCost(Loan loan, int amount, int creditPeriod, BigDecimal payment) {
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount));
        BigDecimal roundInterests = interests.setScale(2, RoundingMode.HALF_UP);
        return roundInterests;
    }

    public static Map<Loan, BigDecimal> sortByPayment(Map<Loan, BigDecimal> mapToSort) {
        Map<Loan, BigDecimal> sortedMap =
                mapToSort.entrySet().stream()
                        .sorted(Entry.comparingByValue())
                        .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }
}
