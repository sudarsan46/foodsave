package com.example.foodsaver.repository;

import com.example.foodsaver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUserUserid(Long userId);

    List<Order> findByFoodProviderUserid(Long providerId);
}
