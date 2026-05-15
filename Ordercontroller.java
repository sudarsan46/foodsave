package com.example.foodsaver.controller;

import com.example.foodsaver.model.OrderResponse;
import com.example.foodsaver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class Ordercontroller {

    @Autowired
    private OrderService orderService;

    // ✅ PLACE ORDER (USER)
    @PostMapping("/place")
    public String placeOrder(
            @RequestParam Long userId,
            @RequestParam Long foodId,
            @RequestParam int quantity
    ) {
        return orderService.placeOrder(userId, foodId, quantity);
    }

    // ✅ GET ORDERS FOR USER (My Orders)
    @GetMapping("/user")
    public List<OrderResponse> getOrdersForUser(
            @RequestParam Long userId
    ) {
        return orderService.getOrdersForUser(userId);
    }

    // ✅ GET ORDERS FOR PROVIDER (Incoming Orders)
    @GetMapping("/provider")
    public List<OrderResponse> getOrdersForProvider(
            @RequestParam Long providerId
    ) {
        return orderService.getOrdersForProvider(providerId);
    }
}
