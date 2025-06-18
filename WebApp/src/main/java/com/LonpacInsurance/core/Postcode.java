package com.LonpacInsurance.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "postcode_mapping")
public class Postcode {
    @Id
    @Column(name = "postcode", length = 5, unique = true, nullable = false)
    private String postcode;

    @Column(name = "town", length = 100, nullable = false)
    private String town;

    public Postcode() {}

    public Postcode(String postcode, String town) {
        this.postcode = postcode;
        this.town = town;
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
}