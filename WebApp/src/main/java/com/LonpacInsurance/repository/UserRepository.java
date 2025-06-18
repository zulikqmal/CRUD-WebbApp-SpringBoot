package com.LonpacInsurance.repository;

import com.LonpacInsurance.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Long countById(Integer id);

    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    Optional<User> findById(@Param("id") Integer userId);
    User findByIcNum(@Param("icNum") String icNum);
}