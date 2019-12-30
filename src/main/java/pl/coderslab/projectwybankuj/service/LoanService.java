package pl.coderslab.projectwybankuj.service;

import org.springframework.stereotype.Service;
import pl.coderslab.projectwybankuj.entity.BigDecimalConverter;
import pl.coderslab.projectwybankuj.entity.Loan;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final BigDecimalConverter bigDecimalConverter;

    public LoanService(BigDecimalConverter bigDecimalConverter) {
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public Map<Loan, Double> calculateLoanPayment(List<Loan> loans, int amount, int creditPeriod) {
        Map<Loan, Double> loansWithPayments = new HashMap<>();

        for (Loan loan : loans) {
            double rateRatio = 1.0 + (loan.getCreditRate() / 100) / 12;
//            BigDecimal rateRatio = bigDecimalConverter.convert(1.0 + (loan.getCreditRate() / 100) / 12, TypeDescriptor.collection(Double.class), BigDecimal.class);
//            BigDecimal rateRatio = BigDecimal.ONE.add((loan.getCreditRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 2, RoundingMode.CEILING));
            double payment = rounded(amount * Math.pow(rateRatio, creditPeriod) * ((rateRatio - 1) / (Math.pow(rateRatio, creditPeriod) - 1)));
//            BigDecimal payment = (amount.multiply(rateRatio.pow(creditPeriod))).multiply((rateRatio.subtract(BigDecimal.ONE)).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.ONE)));
//            BigDecimal payment = bigDecimalConverter.convert(paymentToConvert, Double.class, BigDecimal.class);
            loansWithPayments.put(loan, payment);
        }
        loansWithPayments = sortByPayment(loansWithPayments);
        return loansWithPayments;
    }

    public double calculateChoosenLoanPayment(Loan loan, int amount, int creditPeriod) {
        double rateRatio = 1.0 + (loan.getCreditRate() / 100) / 12;
        double payment = rounded(amount * Math.pow(rateRatio, creditPeriod) * ((rateRatio - 1) / (Math.pow(rateRatio, creditPeriod) - 1)));
        return payment;
    }

    public double calculateServiceCharge(Loan loan, int amount) {
        double serviceCharge = rounded(amount * (loan.getServiceCharge() / 100));
        return serviceCharge;
    }

    public double calculateInsurance(Loan loan, int amount) {
        double insurance = rounded(amount * (loan.getInsurance() / 100));
        return insurance;
    }

    public double calculateInterestsCost(Loan loan, int amount, int creditPeriod, double payment, double serviceCharge, double insurance) {
        double interests = rounded(creditPeriod * payment - amount - serviceCharge - insurance);
        return interests;
    }

    public double calculateTotalCost(Loan loan, int amount, int creditPeriod, double payment) {
        double interests = rounded(creditPeriod * payment - amount);
        return interests;
    }

    public double rounded(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }

    public static Map<Loan, Double> sortByPayment(Map<Loan, Double> mapToSort) {
        Map<Loan, Double> sortedMap =
                mapToSort.entrySet().stream()
                        .sorted(Entry.comparingByValue())
                        .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }
}
