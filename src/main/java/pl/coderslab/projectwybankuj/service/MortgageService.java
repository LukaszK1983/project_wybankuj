package pl.coderslab.projectwybankuj.service;

import org.springframework.stereotype.Service;
import pl.coderslab.projectwybankuj.entity.Mortgage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MortgageService {

    public Map<Mortgage, Double> calculateMortgagePayment(List<Mortgage> mortgages, int amount, int creditPeriod) {
        Map<Mortgage, Double> mortgagesWithPayments = new HashMap<>();

        for (Mortgage mortgage : mortgages) {
            double rateRatio = 1.0 + (mortgage.getCreditRate() / 100) / 12;
            double payment = rounded(amount * Math.pow(rateRatio, creditPeriod) * ((rateRatio - 1) / (Math.pow(rateRatio, creditPeriod) - 1)));
            mortgagesWithPayments.put(mortgage, payment);
        }
        mortgagesWithPayments = sortByPayment(mortgagesWithPayments);
        return mortgagesWithPayments;
    }

    public double calculateChoosenMortgagePayment(Mortgage mortgage, int amount, int creditPeriod) {
        double rateRatio = 1.0 + (mortgage.getCreditRate() / 100) / 12;
        double payment = rounded(amount * Math.pow(rateRatio, creditPeriod) * ((rateRatio - 1) / (Math.pow(rateRatio, creditPeriod) - 1)));
        return payment;
    }

    public double calculateServiceCharge(Mortgage mortgage, int amount) {
        double serviceCharge = rounded(amount * (mortgage.getServiceCharge() / 100));
        return serviceCharge;
    }

    public double calculateInsurance(Mortgage mortgage, int amount) {
        double insurance = rounded(amount * (mortgage.getInsurance() / 100));
        return insurance;
    }

    public double calculateInterestsCost(Mortgage mortgage, int amount, int creditPeriod, double payment, double serviceCharge, double insurance) {
        double interests = rounded(creditPeriod * payment - amount - serviceCharge - insurance);
        return interests;
    }

    public double calculateTotalCost(Mortgage mortgage, int amount, int creditPeriod, double payment) {
        double interests = rounded(creditPeriod * payment - amount);
        return interests;
    }

    public double rounded(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;
        return number;
    }

    public static Map<Mortgage, Double> sortByPayment(Map<Mortgage, Double> mapToSort) {
        Map<Mortgage, Double> sortedMap =
                mapToSort.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }
}
