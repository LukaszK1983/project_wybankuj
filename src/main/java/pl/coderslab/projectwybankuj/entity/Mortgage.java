package pl.coderslab.projectwybankuj.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mortgages")
public class Mortgage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String offer;

    @NotNull
    @DecimalMin(value = "0.1")
    private double creditRate;

    @NotNull
    @DecimalMin(value = "0.0")
    private double serviceCharge;

    @NotNull
    @DecimalMin(value = "0.0")
    private double insurance;

    @NotNull
    @DecimalMin(value = "0.0")
    private double contributionPercent;

    @NotNull
    @Min(1)
    private int minCreditAmount;

    @NotNull
    @Min(2)
    private int maxCreditAmount;

    @NotNull
    @Min(18)
    private int minBorrowerAge;

    @NotNull
    @Min(19)
    private int maxBorrowerAge;

    @NotNull
    @Min(1)
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
