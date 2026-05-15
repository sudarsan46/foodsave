package com.example.foodsaver.repository;

import com.example.foodsaver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    // Return Optional<User> instead of User
    Optional<User> findByEmailid(String emailid);
}




