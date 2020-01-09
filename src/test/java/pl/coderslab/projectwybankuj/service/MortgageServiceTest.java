package pl.coderslab.projectwybankuj.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.coderslab.projectwybankuj.entity.Bank;
import pl.coderslab.projectwybankuj.entity.Mortgage;
import pl.coderslab.projectwybankuj.repository.MortgageRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MortgageServiceTest {

    private MortgageRepository mortgageRepositoryMock = Mockito.mock(MortgageRepository.class);
    private MortgageService mortgageService = new MortgageService(mortgageRepositoryMock);

    @Test
    void shouldCalculateMortgagePayment() {
        // given
        Map<Mortgage, BigDecimal> mapToSort = new HashMap<>();
        int amount = 200000;
        int creditPeriod = 360;
        Bank bank = new Bank();
        Mortgage mortgage1 = new Mortgage(1L, BigDecimal.valueOf(10.0), BigDecimal.valueOf(4.99), BigDecimal.valueOf(0.0), 70, 1000000, 360, 18, 10000, "Test", BigDecimal.valueOf(0.0), bank);
        Mortgage mortgage2 = new Mortgage(1L, BigDecimal.valueOf(10.0), BigDecimal.valueOf(2.99), BigDecimal.valueOf(0.0), 70, 1000000, 360, 18, 10000, "Test", BigDecimal.valueOf(0.0), bank);
        Mortgage mortgage3 = new Mortgage(1L, BigDecimal.valueOf(10.0), BigDecimal.valueOf(3.99), BigDecimal.valueOf(0.0), 70, 1000000, 360, 18, 10000, "Test", BigDecimal.valueOf(0.0), bank);
        List<Mortgage> mortgages = Arrays.asList(mortgage1, mortgage2, mortgage3);

        // when
        for (int i = 0; i < 3; i++) {
            BigDecimal rateRatio = BigDecimal.valueOf(1).add((mortgages.get(i).getCreditRate().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
            BigDecimal totalAmount = BigDecimal.valueOf(amount).add(BigDecimal.valueOf(amount).multiply(mortgages.get(i).getServiceCharge().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING))).add(BigDecimal.valueOf(amount).multiply(mortgages.get(i).getInsurance().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)));
            BigDecimal payment = totalAmount.multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));
            mapToSort.put(mortgages.get(i), payment.setScale(2, RoundingMode.HALF_UP));
        }

        Map<Mortgage, BigDecimal> sortedMap =
                mapToSort.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        // then
        Map<Mortgage, BigDecimal> resultMap = new HashMap<>();
        resultMap.put(mortgage2, BigDecimal.valueOf(842.13));
        resultMap.put(mortgage3, BigDecimal.valueOf(953.68));
        resultMap.put(mortgage1, BigDecimal.valueOf(1072.42));

        assertEquals(resultMap, sortedMap);
    }

    @Test
    void shouldCalculateChoosenMortgagePayment() {
        // given
        Bank bank = new Bank();
        Mortgage mortgage = new Mortgage(1L, BigDecimal.valueOf(10.0), BigDecimal.valueOf(3.99), BigDecimal.valueOf(0.0), 70, 1000000, 360, 18, 10000, "Test", BigDecimal.valueOf(0.0), bank);
        int amount = 200000;
        int creditPeriod = 360;

        // when
        BigDecimal rateRatio = BigDecimal.valueOf(1).add((mortgage.getCreditRate().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)).divide(BigDecimal.valueOf(12), 10, RoundingMode.CEILING));
        BigDecimal totalAmount = BigDecimal.valueOf(amount).add(BigDecimal.valueOf(amount).multiply(mortgage.getServiceCharge().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING))).add(BigDecimal.valueOf(amount).multiply(mortgage.getInsurance().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING)));
        BigDecimal payment = totalAmount.multiply(rateRatio.pow(creditPeriod)).multiply((rateRatio.subtract(BigDecimal.valueOf(1))).divide((rateRatio.pow(creditPeriod)).subtract(BigDecimal.valueOf(1)), 10, RoundingMode.CEILING));

        // then
        assertEquals(BigDecimal.valueOf(953.68), payment.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateServiceCharge() {
        // given
        Bank bank = new Bank();
        Mortgage mortgage = new Mortgage(1L, BigDecimal.valueOf(10.0), BigDecimal.valueOf(3.99), BigDecimal.valueOf(0.0), 70, 1000000, 360, 18, 10000, "Test", BigDecimal.valueOf(1.0), bank);
        int amount = 200000;

        // when
        BigDecimal serviceCharge = BigDecimal.valueOf(amount).multiply(mortgage.getServiceCharge().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));

        // then
        assertEquals(BigDecimal.valueOf(2000.0), serviceCharge.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateInsurance() {
        // given
        Bank bank = new Bank();
        Mortgage mortgage = new Mortgage(1L, BigDecimal.valueOf(10.0), BigDecimal.valueOf(3.99), BigDecimal.valueOf(1.0), 70, 1000000, 360, 18, 10000, "Test", BigDecimal.valueOf(1.0), bank);
        int amount = 200000;

        // when
        BigDecimal insurance = BigDecimal.valueOf(amount).multiply(mortgage.getInsurance().divide(BigDecimal.valueOf(100), 10, RoundingMode.CEILING));

        // then
        assertEquals(BigDecimal.valueOf(2000.0), insurance.setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateInterestsCost() {
        // given
        int amount = 200000;
        int creditPeriod = 360;
        BigDecimal payment = BigDecimal.valueOf(1000);
        BigDecimal serviceCharge = BigDecimal.valueOf(1000);
        BigDecimal insurance = BigDecimal.valueOf(2000);

        // when
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount)).subtract(serviceCharge).subtract(insurance);

        // then
        assertEquals(BigDecimal.valueOf(157000), interests.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateTotalCost() {
        // given
        int amount = 200000;
        int creditPeriod = 360;
        BigDecimal payment = BigDecimal.valueOf(1000);

        // when
        BigDecimal interests = BigDecimal.valueOf(creditPeriod).multiply(payment).subtract(BigDecimal.valueOf(amount));

        // then
        assertEquals(BigDecimal.valueOf(160000), interests.setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    void shouldChooseOffersWithoutInsuranceAndServiceCharge() {
        // given
        String chooseInsurance = "no";
        String chooseServiceCharge = "no";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;
        BigDecimal contributionPercent = BigDecimal.valueOf(10);

        // when
        mortgageService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age, contributionPercent);

        // then
        Mockito.verify(mortgageRepositoryMock).findAllByParametersWithoutInsuranceAndServiceCharge(amount, creditPeriod, age, contributionPercent);
    }

    @Test
    void shouldChooseOffersWithoutInsurance() {
        // given
        String chooseInsurance = "no";
        String chooseServiceCharge = "yes";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;
        BigDecimal contributionPercent = BigDecimal.valueOf(10);

        // when
        mortgageService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age, contributionPercent);

        // then
        Mockito.verify(mortgageRepositoryMock).findAllByParametersWithoutInsurance(amount, creditPeriod, age, contributionPercent);
    }

    @Test
    void shouldChooseOffersWithoutServiceCharge() {
        // given
        String chooseInsurance = "yes";
        String chooseServiceCharge = "no";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;
        BigDecimal contributionPercent = BigDecimal.valueOf(10);

        // when
        mortgageService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age, contributionPercent);

        // then
        Mockito.verify(mortgageRepositoryMock).findAllByParametersWithoutServiceCharge(amount, creditPeriod, age, contributionPercent);
    }

    @Test
    void shouldChooseOffersWithInsuranceAndServiceCharge() {
        // given
        String chooseInsurance = "yes";
        String chooseServiceCharge = "yes";
        int amount = 50000;
        int creditPeriod = 60;
        int age = 25;
        BigDecimal contributionPercent = BigDecimal.valueOf(10);

        // when
        mortgageService.chooseOffers(chooseInsurance, chooseServiceCharge, amount, creditPeriod, age, contributionPercent);

        // then
        Mockito.verify(mortgageRepositoryMock).findAllByParameters(amount, creditPeriod, age, contributionPercent);
    }
}