package com.example.foodsaver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodid;

    @Column(name = "foodname", nullable = false)
    private String foodname;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @Column(name = "expire_time", nullable = false)
    private Long expireTime;

    @Column(nullable = false)
    private String location;

    @Column(name = "active_status", nullable = false)
    private String activestatus;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private User provider;

    public Food() {}

    public Long getFoodid() { return foodid; }
    public void setFoodid(Long foodid) { this.foodid = foodid; }

    public String getFoodname() { return foodname; }
    public void setFoodname(String foodname) { this.foodname = foodname; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.activestatus = (quantity <= 0) ? "INACTIVE" : "ACTIVE";
    }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public Long getExpireTime() { return expireTime; }
    public void setExpireTime(Long expireTime) { this.expireTime = expireTime; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getActivestatus() { return activestatus; }
    public void setActivestatus(String activestatus) { this.activestatus = activestatus; }

    public User getProvider() { return provider; }
    public void setProvider(User provider) { this.provider = provider; }
}
