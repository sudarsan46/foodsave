package com.example.foodsaver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private int quantity;
    private int orderTime;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getOrderTime() { return orderTime; }
    public void setOrderTime(int orderTime) { this.orderTime = orderTime; }

    public Food getFood() { return food; }
    public void setFood(Food food) { this.food = food; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
