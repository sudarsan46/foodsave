package com.example.foodsaver.service;

import com.example.foodsaver.model.User;
import com.example.foodsaver.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



    @Service
    public class Userservice {

        @Autowired
        private UserRepo userrepo;

        // Register new user
        public User userregister(String name, String emailid, String password, String role) {

            if (userrepo.findByEmailid(emailid).isPresent()) {
                throw new RuntimeException("Email already registered");
            }

            role = role.toUpperCase();
            User user = new User(name, emailid, password, role);
            return userrepo.save(user);
        }

        // Login user
        public User login(String emailid, String password) {

            User user = userrepo.findByEmailid(emailid)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password");
            }

            return user;
        }
    }






