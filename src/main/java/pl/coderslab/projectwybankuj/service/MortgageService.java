package pl.coderslab.projectwybankuj.service;

import org.springframework.stereotype.Service;
import pl.coderslab.projectwybankuj.entity.Mortgage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MortgageService {

    public Map<Mortgage, BigDecimal> calculateMortgagePayment(List<Mortgage> mortgages, int amount, int creditPeriod) {
        Map<Mortgage, BigDecimal> mortgagesWithPayments = new HashMap<>();

        for (Mortgage mortgage : mortgages) {
            BigDecimal rateRatio = BigDecimal.valueOf(1).add((BigDecimal.valueOf(mortgage.getCreditRate()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
            BigDecimal payment = BigDecimal.valueOf(amount).multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
            BigDecimal roundPayment = payment.setScale(2, RoundingMode.HALF_UP);
            mortgagesWithPayments.put(mortgage, roundPayment);
        }
        mortgagesWithPayments = sortByPayment(mortgagesWithPayments);
        return mortgagesWithPayments;
    }

    public BigDecimal calculateChoosenMortgagePayment(Mortgage mortgage, int amount, int creditPeriod) {
        BigDecimal rateRatio = BigDecimal.valueOf(1).add((BigDecimal.valueOf(mortgage.getCreditRate()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
        BigDecimal payment = BigDecimal.valueOf(amount).multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
        BigDecimal roundPayment = payment.setScale(2, RoundingMode.HALF_UP);
        return roundPayment;
    }

    public BigDecimal calculateServiceCharge(Mortgage mortgage, int amount) {
        BigDecimal serviceCharge = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(mortgage.getServiceCharge()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));
        BigDecimal roundServiceCharge = serviceCharge.setScale(2, RoundingMode.HALF_UP);
        return roundServiceCharge;
    }

    public BigDecimal calculateInsurance(Mortgage mortgage, int amount) {
        BigDecimal insurance = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(mortgage.getInsurance()).divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));
        BigDecimal roundInsurance = insurance.setScale(2, RoundingMode.HALF_UP);
        return roundInsurance;
    }

    public BigDecimal calculateInterestsCost(Mortgage mortgage, int amount, int creditPeriod, BigDecimal payment, BigDecimal serviceCharge, BigDecimal insurance) {
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount)).subtract(serviceCharge).subtract(insurance);
        BigDecimal roundInterests = interests.setScale(2, RoundingMode.HALF_UP);
        return roundInterests;
    }

    public BigDecimal calculateTotalCost(Mortgage mortgage, int amount, int creditPeriod, BigDecimal payment) {
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount));
        BigDecimal roundInterests = interests.setScale(2, RoundingMode.HALF_UP);
        return roundInterests;
    }

    public double rounded(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }

    public static Map<Mortgage, BigDecimal> sortByPayment(Map<Mortgage, BigDecimal> mapToSort) {
        Map<Mortgage, BigDecimal> sortedMap =
                mapToSort.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
//        BinaryOperator<List<Double>> compareFunction = (e1, e2) -> e1;
//
//        Map<Mortgage, Double> sortedMap =
//                mapToSort.entrySet().stream()
//                    .sorted(Map.Entry.comparingByValue())
//                    .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue, compareFunction));
        return sortedMap;
    }
}
