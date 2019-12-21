package pl.coderslab.projectwybankuj.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "agencies")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    private String agencyName;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String street;

    @NotEmpty
    @Size(max = 10)
    private String streetNumber;

    @NotEmpty
    @Pattern(regexp = "^\\d{2}\\-\\d{3}$")
    private String zipCode;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String city;

    @NotEmpty
    @Pattern(regexp = "\\d{9}")
    private String phone;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String hours;

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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}