package com.example.foodsaver.model;

public class OrderResponse {

    private Long orderId;
    private String foodName;
    private int quantity;
    private String userName;
    private Long userId;
    private int orderTime;

    public OrderResponse(Long orderId, String foodName, int quantity,
                         String userName, Long userId, int orderTime) {
        this.orderId = orderId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.userName = userName;
        this.userId = userId;
        this.orderTime = orderTime;
    }

    public Long getOrderId() { return orderId; }
    public String getFoodName() { return foodName; }
    public int getQuantity() { return quantity; }
    public String getUserName() { return userName; }
    public Long getUserId() { return userId; }
    public int getOrderTime() { return orderTime; }
}
