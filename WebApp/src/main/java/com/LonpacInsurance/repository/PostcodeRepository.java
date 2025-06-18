package com.LonpacInsurance.repository;


import com.LonpacInsurance.core.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostcodeRepository extends JpaRepository<Postcode, String> {
    Postcode findByPostcode(String postcode);
}