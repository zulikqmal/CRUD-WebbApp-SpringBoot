package com.LonpacInsurance.service;

import com.LonpacInsurance.repository.PostcodeRepository;
import com.LonpacInsurance.core.Postcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostcodeService {
    @Autowired
    private PostcodeRepository repo;

    public String getTownByPostcode(String postcode) {
        System.out.println("PostcodeService: Searching for postcode = '" + postcode + "'");
        Postcode postcodeMapping = repo.findByPostcode(postcode);
        String foundTown = postcodeMapping != null ? postcodeMapping.getTown() : null;
        System.out.println("PostcodeService: Found town = '" + foundTown + "' for postcode = '" + postcode + "'");
        return foundTown;
    }

    public void savePostcodeMapping(Postcode postcode) {
        repo.save(postcode);
    }
}