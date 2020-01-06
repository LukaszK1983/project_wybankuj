package pl.coderslab.projectwybankuj.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "logos")
public class Logo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String logoName;
    private String fileType;

    @Lob
    private byte[] data;

//    @ManyToOne
//    @JoinColumn(name = "bank_id")
//    private Bank bank;

    public Logo(String logoName, String fileType, byte[] data) {
        this.logoName = logoName;
        this.fileType = fileType;
        this.data = data;
    }

    public Logo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

//    public Bank getBank() {
//        return bank;
//    }
//
//    public void setBank(Bank bank) {
//        this.bank = bank;
//    }
}
