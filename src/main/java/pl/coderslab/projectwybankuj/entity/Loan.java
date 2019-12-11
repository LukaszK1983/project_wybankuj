package pl.coderslab.projectwybankuj.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String offer;

    @NotEmpty
    private double creditRate;

    @NotEmpty
    private double serviceCharge;

    private double insurance;

    @NotEmpty
    private int minCreditAmount;

    @NotEmpty
    private int maxCreditAmount;

    @NotEmpty
    private int minBorrowerAge;

    @NotEmpty
    private int maxBorrowerAge;

    @NotEmpty
    private int maxCreditPeriod;

    @ManyToOne
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
