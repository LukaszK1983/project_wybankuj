package pl.coderslab.projectwybankuj.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.entity.Loan;
import pl.coderslab.projectwybankuj.repository.LoanRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanServiceTest {

    private LoanRepository loanRepositoryMock = Mockito.mock(LoanRepository.class);
    private LoanService loanService = new LoanService(loanRepositoryMock);

    @Test
    void shouldCalculateLoanPayment() {
        // given
        int amount = 50000;
        int creditPeriod = 60;
        Bank bank = new Bank();
        Loan loan = new Loan(1L, BigDecimal.valueOf(9.99), BigDecimal.valueOf(0.0), 70, 100000, 120, 18, 1000, "Test", BigDecimal.valueOf(0.0), bank);
        Loan loan2 = new Loan(2L, BigDecimal.valueOf(7.99), BigDecimal.valueOf(0.0), 70, 100000, 120, 18, 1000, "Test", BigDecimal.valueOf(0.0), bank);
        Loan loan3 = new Loan(3L, BigDecimal.valueOf(8.99), BigDecimal.valueOf(0.0), 70, 100000, 120, 18, 1000, "Test", BigDecimal.valueOf(0.0), bank);
        List<Loan> loans = Arrays.asList(loan, loan2, loan3);

        // when
        Map<Loan, BigDecimal> sortedMap = shouldCalculatePaymentAndSortList(loans, amount, creditPeriod);

        // then
        Map<Loan, BigDecimal> resultMap = new HashMap<>();
        resultMap.put(loan2, BigDecimal.valueOf(1013.58));
        resultMap.put(loan3, BigDecimal.valueOf(1037.68));
        resultMap.put(loan, BigDecimal.valueOf(1062.11));

        assertEquals(resultMap, sortedMap);
    }

    @Test
    void shouldCalculateChoosenLoanPayment() {
        // given
        Bank bank = new Bank();
        Loan loan = new Loan(1L, BigDecimal.valueOf(7.99), BigDecimal.valueOf(0.0), 70, 100000, 120, 18, 1000, "Test", BigDecimal.valueOf(0.0), bank);
        int amount = 50000;
        int creditPeriod = 60;

        // when
        BigDecimal payment = shouldCalculatePayment(loan, amount, creditPeriod);

        // then
        assertEquals(BigDecimal.valueOf(1013.58), payment.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateServiceCharge() {
        // given
        Bank bank = new Bank();
        Loan loan = new Loan(1L, BigDecimal.valueOf(7.99), BigDecimal.valueOf(0.0), 70, 100000, 120, 18, 1000, "Test", BigDecimal.valueOf(10.0), bank);
        int amount = 50000;

        // when
        BigDecimal serviceCharge = BigDecimal.valueOf(amount).multiply(loan.getServiceCharge().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));

        // then
        assertEquals(BigDecimal.valueOf(5000.00), serviceCharge.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateInsurance() {
        // given
        Bank bank = new Bank();
        Loan loan = new Loan(1L, BigDecimal.valueOf(7.99), BigDecimal.valueOf(10.0), 70, 100000, 120, 18, 1000, "Test", BigDecimal.valueOf(10.0), bank);
        int amount = 50000;

        // when
        BigDecimal insurance = BigDecimal.valueOf(amount).multiply(loan.getInsurance().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));

        // then
        assertEquals(BigDecimal.valueOf(5000.00), insurance.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateInterestsCost() {
        // given
        int amount = 50000;
        int creditPeriod = 60;
        BigDecimal payment = BigDecimal.valueOf(1500);
        BigDecimal serviceCharge = BigDecimal.valueOf(1000);
        BigDecimal insurance = BigDecimal.valueOf(2000);

        // when
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount)).subtract(serviceCharge).subtract(insurance);

        // then
        assertEquals(BigDecimal.valueOf(37000.00), interests.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateTotalCost() {
        // given
        int amount = 50000;
        int creditPeriod = 60;
        BigDecimal payment = BigDecimal.valueOf(1500);

        // when
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount));

        // then
        assertEquals(BigDecimal.valueOf(40000.00), interests.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void shouldChooseOffersWithoutInsuranceAndServiceCharge() {
        // given
        String chooseInsurance = "no";
        String chooseServiceCharge = "no";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;

        // when
        loanService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age);

        // then
        Mockito.verify(loanRepositoryMock).findAllByParametersWithoutInsuranceAndServiceCharge(amount, creditPeriod, age);
    }

    @Test
    void shouldChooseOffersWithoutInsurance() {
        // given
        String chooseInsurance = "no";
        String chooseServiceCharge = "yes";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;

        // when
        loanService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age);

        // then
        Mockito.verify(loanRepositoryMock).findAllByParametersWithoutInsurance(amount, creditPeriod, age);
    }

    @Test
    void shouldChooseOffersWithoutServiceCharge() {
        // given
        String chooseInsurance = "yes";
        String chooseServiceCharge = "no";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;

        // when
        loanService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age);

        // then
        Mockito.verify(loanRepositoryMock).findAllByParametersWithoutServiceCharge(amount, creditPeriod, age);
    }

    @Test
    void shouldChooseOffersWithInsuranceAndServiceCharge() {
        // given
        String chooseInsurance = "yes";
        String chooseServiceCharge = "yes";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;

        // when
        loanService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age);

        // then
        Mockito.verify(loanRepositoryMock).findAllByParameters(amount, creditPeriod, age);
    }

    private Map<Loan, BigDecimal> shouldCalculatePaymentAndSortList(List<Loan> loans, int amount, int creditPeriod) {
        Map<Loan, BigDecimal> mapToSort = new HashMap<>();
        for (Loan loan : loans) {
            BigDecimal rateRatio = BigDecimal.valueOf(1).add((loan.getCreditRate().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
            BigDecimal totalAmount = BigDecimal.valueOf(amount).add(BigDecimal.valueOf(amount).multiply(loan.getServiceCharge().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING))).add(BigDecimal.valueOf(amount).multiply(loan.getInsurance().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)));
            BigDecimal payment = totalAmount.multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
            mapToSort.put(loan, payment.setScale(2, RoundingMode.HALF_UP));
        }

        return mapToSort.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    private BigDecimal shouldCalculatePayment(Loan loan, int amount, int creditPeriod) {
        BigDecimal rateRatio = BigDecimal.valueOf(1).add((loan.getCreditRate().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
        BigDecimal totalAmount = BigDecimal.valueOf(amount).add(BigDecimal.valueOf(amount).multiply(loan.getServiceCharge().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING))).add(BigDecimal.valueOf(amount).multiply(loan.getInsurance().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)));
        return totalAmount.multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
    }
}