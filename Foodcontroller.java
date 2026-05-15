package com.example.foodsaver.controller;

import com.example.foodsaver.model.Food;
import com.example.foodsaver.service.Foodservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food")
public class Foodcontroller {

    @Autowired
    private Foodservice foodservice;

    @PostMapping("/available")
    public ResponseEntity<?> addFood(
            @RequestParam String foodname,
            @RequestParam int quantity,
            @RequestParam int price,
            @RequestParam long expireTime,
            @RequestParam String location,
            @RequestParam Long providerId
    ) {
        try {
            return ResponseEntity.ok(
                    foodservice.addFood(foodname, quantity, price, expireTime, location, providerId)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Food>> getAvailableFood(@RequestParam Long userId) {
        return ResponseEntity.ok(foodservice.getAvailableFood(userId));
    }
}
