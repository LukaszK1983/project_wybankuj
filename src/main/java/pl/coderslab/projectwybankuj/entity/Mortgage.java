package pl.coderslab.projectwybankuj.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

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
    @DecimalMax(value = "10.0")
    private BigDecimal creditRate;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal serviceCharge;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal insurance;

    @NotNull
    @DecimalMin(value = "10.0")
    private BigDecimal contributionPercent;

    @NotNull
    @Min(value = 1)
    private Integer minCreditAmount;

    @NotNull
    @Min(value = 2)
    private Integer maxCreditAmount;

    @NotNull
    @Min(value = 18)
    private Integer minBorrowerAge;

    @NotNull
    @Min(value = 19)
    private Integer maxBorrowerAge;

    @NotNull
    @Min(value = 1)
    private Integer maxCreditPeriod;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @NotNull
    private Bank bank;

    public Mortgage() {
    }

    public Mortgage(Long id, BigDecimal contributionPercent, BigDecimal creditRate, BigDecimal insurance, int maxBorrowerAge, int maxCreditAmount, int maxCreditPeriod, int minBorrowerAge, int minCreditAmount, String offer, BigDecimal serviceCharge, Bank bank) {
        this.id = id;
        this.contributionPercent = contributionPercent;
        this.creditRate = creditRate;
        this.insurance = insurance;
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

    public BigDecimal getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(BigDecimal creditRate) {
        this.creditRate = creditRate;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getContributionPercent() {
        return contributionPercent;
    }

    public void setContributionPercent(BigDecimal contributionPercent) {
        this.contributionPercent = contributionPercent;
    }

    public Integer getMinCreditAmount() {
        return minCreditAmount;
    }

    public void setMinCreditAmount(Integer minCreditAmount) {
        this.minCreditAmount = minCreditAmount;
    }

    public Integer getMaxCreditAmount() {
        return maxCreditAmount;
    }

    public void setMaxCreditAmount(Integer maxCreditAmount) {
        this.maxCreditAmount = maxCreditAmount;
    }

    public Integer getMinBorrowerAge() {
        return minBorrowerAge;
    }

    public void setMinBorrowerAge(Integer minBorrowerAge) {
        this.minBorrowerAge = minBorrowerAge;
    }

    public Integer getMaxBorrowerAge() {
        return maxBorrowerAge;
    }

    public void setMaxBorrowerAge(Integer maxBorrowerAge) {
        this.maxBorrowerAge = maxBorrowerAge;
    }

    public Integer getMaxCreditPeriod() {
        return maxCreditPeriod;
    }

    public void setMaxCreditPeriod(Integer maxCreditPeriod) {
        this.maxCreditPeriod = maxCreditPeriod;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
