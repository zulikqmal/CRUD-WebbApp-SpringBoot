package com.LonpacInsurance;

import com.LonpacInsurance.repository.UserRepository;
import com.LonpacInsurance.core.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Rollback(false)
    public void testAddNew() {
        User user = new User();

        user.setIcNum("970820-10-9999");
        user.setGender(User.Gender.Male);
        user.setDob(LocalDate.of(1997, 8, 20));
        user.setPostcode("12345");
        user.setTown("Johor Bahru");

        User savedUser = repo.save(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getIcNum()).isNotNull().isNotEmpty();
        assertThat(savedUser.getGender()).isNotNull();
        assertThat(savedUser.getDob()).isNotNull();
        assertThat(savedUser.getPostcode()).isNotNull();
        assertThat(savedUser.getTown()).isNotNull();
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        Integer userId = 62;
        Optional<User> optionalUser  = repo.findById(userId);

        if (optionalUser .isPresent()) {
            User user = optionalUser .get();

            user.setTown("Pulau Pinang");
            repo.save(user);

            User updatedUser  = repo.findById(userId).get();
            assertThat(updatedUser .getTown()).isEqualTo("Pulau Pinang");
        } else {
            Assertions.fail("User  with ID " + userId + " not found.");
        }
    }

    @Test
    public void testGet() {
        Integer userId = 62;
        Optional<User> optionalUser  = repo.findById(userId);
        assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Integer userId = 62;
        Optional<User> optionalUser  = repo.findById(userId);
        if (optionalUser .isPresent()) {
            User user = optionalUser .get();

            repo.delete(user);

            assertThat(repo.findById(userId)).isNotPresent();
        } else {
            Assertions.fail("User  with ID " + userId + " not found.");
        }
    }
}