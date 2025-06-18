package com.LonpacInsurance.controller;

import com.LonpacInsurance.service.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LocationRestController {

    @Autowired
    private PostcodeService postcodeService;

    @GetMapping("/api/postcode-to-town")
    public ResponseEntity<Map<String, String>> getTownByPostcode(
            @RequestParam(required = false) String postcode) {

        System.out.println("RestController: Received postcode = '" + postcode + "'");

        String town = "";
        if (postcode != null && !postcode.isEmpty()) {
            String foundTown = postcodeService.getTownByPostcode(postcode);
            if (foundTown != null) {
                town = foundTown;
            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("town", town);
        System.out.println("RestController: Returning response = " + response);
        return ResponseEntity.ok(response);
    }
}