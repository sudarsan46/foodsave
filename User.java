package com.example.foodsaver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_PROVIDER = "PROVIDER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    @Column(nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String emailid;

    @Column(nullable = false)
    private String password; // stored hashed

    @Column(name = "role", nullable = false)
    private String role;

    public User() {}

    public User(String name, String emailid, String password, String role) {
        this.name = name;
        this.emailid = emailid;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public long getUserid() { return userid; }
    public void setUserid(long userid) { this.userid = userid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmailid() { return emailid; }
    public void setEmailid(String emailid) { this.emailid = emailid; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}


