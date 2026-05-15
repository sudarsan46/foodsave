package com.example.foodsaver.service;

import com.example.foodsaver.model.Food;
import com.example.foodsaver.model.User;
import com.example.foodsaver.repository.FoodRepo;
import com.example.foodsaver.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Foodservice {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FoodRepo foodRepo;

    public Food addFood(String foodname, int quantity, int price,
                        long expireTime, String location, Long providerId) {

        User provider = userRepo.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        if (!"PROVIDER".equalsIgnoreCase(provider.getRole())) {
            throw new RuntimeException("Only PROVIDER can add food");
        }

        Food food = new Food();
        food.setFoodname(foodname);
        food.setQuantity(quantity); // auto sets ACTIVE
        food.setPrice(price);
        food.setExpireTime(expireTime);
        food.setLocation(location);
        food.setProvider(provider);

        // 🔥 SAFETY FIX
        food.setActivestatus("ACTIVE");

        return foodRepo.save(food);
    }

    // 🔥 FIXED METHOD (removed expire filter)
    public List<Food> getAvailableFood(Long userId) {

        return foodRepo
                .findByActivestatusIgnoreCaseAndQuantityGreaterThan(
                        "ACTIVE", 0
                );
    }
}
