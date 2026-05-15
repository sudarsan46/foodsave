package com.example.foodsaver.service;

import com.example.foodsaver.model.*;
import com.example.foodsaver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Transactional
    public String placeOrder(Long userId, Long foodId, int quantity) {

        // 1. Validate user
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!"USER".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("Only USER can place orders");
        }

        // 2. Validate food
        Food food = foodRepo.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // 3. Check status
        if (!"ACTIVE".equalsIgnoreCase(food.getActivestatus())) {
            throw new RuntimeException("Food not available");
        }

        // 4. Check quantity
        if (food.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient quantity");
        }

        // 5. Reduce quantity
        food.setQuantity(food.getQuantity() - quantity);
        foodRepo.save(food);

        // 6. Create order
        Order order = new Order();
        order.setUser(user);
        order.setFood(food);
        order.setQuantity(quantity);
        order.setOrderTime((int) (System.currentTimeMillis() / 1000));

        orderRepo.save(order);

        return "Order placed successfully";
    }

    // PROVIDER VIEW
    public List<OrderResponse> getOrdersForProvider(Long providerId) {

        List<Order> orders = orderRepo.findByFoodProviderUserid(providerId);

        return orders.stream().map(order ->
                new OrderResponse(
                        order.getOrderId(),
                        order.getFood().getFoodname(),
                        order.getQuantity(),
                        order.getUser().getName(),
                        order.getUser().getUserid(),
                        order.getOrderTime()
                )
        ).collect(Collectors.toList());
    }

    // USER VIEW
    public List<OrderResponse> getOrdersForUser(Long userId) {

        List<Order> orders = orderRepo.findByUserUserid(userId);

        return orders.stream().map(order ->
                new OrderResponse(
                        order.getOrderId(),
                        order.getFood().getFoodname(),
                        order.getQuantity(),
                        order.getUser().getName(),
                        order.getUser().getUserid(),
                        order.getOrderTime()
                )
        ).collect(Collectors.toList());
    }
}
