package pl.coderslab.projectwybankuj.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "loans")
public class Loan {

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

    public Loan(Long id, double creditRate, double insurance, int maxBorrowerAge, int maxCreditAmount, int maxCreditPeriod, int minBorrowerAge, int minCreditAmount, String offer, double serviceCharge, Bank bank) {
        this.id = id;
        this.creditRate = creditRate;
        this.maxBorrowerAge = maxBorrowerAge;
        this.maxCreditAmount = maxCreditAmount;
        this.maxCreditPeriod = maxCreditPeriod;
        this.minBorrowerAge = minBorrowerAge;
        this.minCreditAmount = minCreditAmount;
        this.offer = offer;
        this.serviceCharge = serviceCharge;
        this.bank = bank;
    }

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
