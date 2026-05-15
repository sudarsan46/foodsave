package com.example.foodsaver.controller;

import com.example.foodsaver.model.User;
import com.example.foodsaver.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class Usercontroller {

    @Autowired
    private Userservice userservice;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String name,
            @RequestParam String emailid,
            @RequestParam String password,
            @RequestParam String role
    ) {
        try {
            User user = userservice.userregister(name, emailid, password, role);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("SUCCESS:ID:" + user.getUserid());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String emailid,
            @RequestParam String password
    ) {
        try {
            User user = userservice.login(emailid, password);
            return ResponseEntity.ok("SUCCESS:" + user.getRole() + ":" + user.getUserid());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
