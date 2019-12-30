package pl.coderslab.projectwybankuj.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "mortgages")
public class Mortgage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String offer;

    @NotNull
    @DecimalMin(value = "0.1", message = "Minimalna wartość to 0.1")
    @DecimalMax(value = "10.0", message = "Maksymalna wartość to 10")
    private double creditRate;

    @NotNull
    @DecimalMin(value = "0.0", message = "Minimalna wartość to 0")
    private double serviceCharge;

    @NotNull
    @DecimalMin(value = "0.0", message = "Minimalna wartość to 0")
    private double insurance;

    @NotNull
    @DecimalMin(value = "10.0", message = "Minimalna wartość to 10")
    private double contributionPercent;

    @NotNull
    @Min(value = 1, message = "Minimalna kwota kredytu to 1")
    private int minCreditAmount;

    @NotNull
    @Min(value = 2, message = "Minimalna kwota kredytu to 2")
    private int maxCreditAmount;

    @NotNull
    @Min(value = 18, message = "Minimalna wartość to 18")
    private int minBorrowerAge;

    @NotNull
    @Min(value = 19, message = "Minimalna wartość to 19")
    private int maxBorrowerAge;

    @NotNull
    @Min(value = 1, message = "Minimalna wartość to 1")
    private int maxCreditPeriod;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @NotNull
    private Bank bank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public double getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(double creditRate) {
        this.creditRate = creditRate;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getContributionPercent() {
        return contributionPercent;
    }

    public void setContributionPercent(double contributionPercent) {
        this.contributionPercent = contributionPercent;
    }

    public int getMinCreditAmount() {
        return minCreditAmount;
    }

    public void setMinCreditAmount(int minCreditAmount) {
        this.minCreditAmount = minCreditAmount;
    }

    public int getMaxCreditAmount() {
        return maxCreditAmount;
    }

    public void setMaxCreditAmount(int maxCreditAmount) {
        this.maxCreditAmount = maxCreditAmount;
    }

    public int getMinBorrowerAge() {
        return minBorrowerAge;
    }

    public void setMinBorrowerAge(int minBorrowerAge) {
        this.minBorrowerAge = minBorrowerAge;
    }

    public int getMaxBorrowerAge() {
        return maxBorrowerAge;
    }

    public void setMaxBorrowerAge(int maxBorrowerAge) {
        this.maxBorrowerAge = maxBorrowerAge;
    }

    public int getMaxCreditPeriod() {
        return maxCreditPeriod;
    }

    public void setMaxCreditPeriod(int maxCreditPeriod) {
        this.maxCreditPeriod = maxCreditPeriod;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
