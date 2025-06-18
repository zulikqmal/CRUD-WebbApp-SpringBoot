package com.LonpacInsurance.service;

import com.LonpacInsurance.core.Postcode;
import com.LonpacInsurance.core.User;
import com.LonpacInsurance.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private PostcodeService postcodeService;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        if (user.getIcNum() != null && user.getDob() == null) {
            try {
                LocalDate dobFromIc = extractDobFromIc(user.getIcNum());
                user.setDob(dobFromIc);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid IC Number format for DOB extraction: " + user.getIcNum());
            }

            try {
                if (user.getPostcode() != null) {
                    String town = postcodeService.getTownByPostcode(user.getPostcode());
                    if (town != null) {
                        user.setTown(town);
                    }
                } else {
                    System.err.println("User postal code is null.");
                }
            } catch (Exception e) {
                System.err.println("Error while finding town by postal code: " + e.getMessage());
            }
        }
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(id);    }

    /**
     * Extracts Date of Birth from a Malaysian IC Number.
     * Assumes IC format YYMMDD-XX-XXXX.
     *
     * @param icNum The Malaysian IC number.
     * @return The LocalDate representing the Date of Birth.
     * @throws IllegalArgumentException if the IC number format is invalid for DOB extraction.
     */
    private LocalDate extractDobFromIc(String icNum) {
        if (icNum == null || icNum.length() < 6) {
            throw new IllegalArgumentException("Invalid IC number format for DOB extraction: too short.");
        }
        String dobPart = icNum.substring(0, 6);
        try {
            int yearPrefix = Integer.parseInt(dobPart.substring(0, 2));
            String fullYear;
            if (yearPrefix >= 0 && yearPrefix <= LocalDate.now().getYear() % 100) {
                fullYear = "20" + dobPart.substring(0, 2);
            } else {
                fullYear = "19" + dobPart.substring(0, 2);
            }
            String fullDobString = fullYear + dobPart.substring(2);
            return LocalDate.parse(fullDobString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new IllegalArgumentException("Failed to parse DOB from IC number: " + icNum, e);
        }
    }
}