package com.LonpacInsurance.core;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ic_num", length = 14, unique = true)
    private String icNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "postcode", length = 5)
    private String postcode;

    @Column(name = "town", length = 100)
    private String town;

    public enum Gender {
        Male,
        Female,
    }

    public User() {
    }
    public User(Integer id, String icNum, Gender gender, LocalDate dob, String postcode, String town) {
        this.id = id;
        this.icNum = icNum;
        this.gender = gender;
        this.dob = dob;
        this.postcode = postcode;
        this.town = town;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getIcNum() { return icNum; }
    public void setIcNum(String icNum) {
        this.icNum = icNum;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", IC Number='" + icNum + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", postcode='" + postcode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}