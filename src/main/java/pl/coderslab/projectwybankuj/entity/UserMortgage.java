package pl.coderslab.projectwybankuj.entity;

public class UserMortgage {
    private int amount;
    private int creditPeriod;
    private int age;
    private double contributionPercent;
    private String chooseServiceCharge;
    private String chooseInsurance;

    public UserMortgage() {
    }

    public UserMortgage(int amount, int creditPeriod, int age, double contributionPercent, String chooseServiceCharge, String chooseInsurance) {
        this.amount = amount;
        this.creditPeriod = creditPeriod;
        this.age = age;
        this.contributionPercent = contributionPercent;
        this.chooseServiceCharge = chooseServiceCharge;
        this.chooseInsurance = chooseInsurance;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCreditPeriod() {
        return creditPeriod;
    }

    public void setCreditPeriod(int creditPeriod) {
        this.creditPeriod = creditPeriod;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getContributionPercent() {
        return contributionPercent;
    }

    public void setContributionPercent(double contributionPercent) {
        this.contributionPercent = contributionPercent;
    }

    public String getChooseServiceCharge() {
        return chooseServiceCharge;
    }

    public void setChooseServiceCharge(String chooseServiceCharge) {
        this.chooseServiceCharge = chooseServiceCharge;
    }

    public String getChooseInsurance() {
        return chooseInsurance;
    }

    public void setChooseInsurance(String chooseInsurance) {
        this.chooseInsurance = chooseInsurance;
    }
}
