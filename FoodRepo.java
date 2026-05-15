package com.example.foodsaver.repository;

import com.example.foodsaver.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {

    // 🔥 FIXED (removed expireTime filter)
    List<Food> findByActivestatusIgnoreCaseAndQuantityGreaterThan(
            String activestatus, int quantity
    );

    List<Food> findByFoodname(String foodname);
}
