package com.example.backendproject.entity;

import jakarta.persistence.*;

// 주문 추가
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String foodId;
    private int count;

    protected Order() {}

    public Order(Long id, String userId, String foodId, int count) {
        this.id = id;
        this.userId = userId;
        this.foodId = foodId;
        this.count = count;
    }

    public Order(String userId, String foodId, int count) {
        this.userId = userId;
        this.foodId = foodId;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getFoodId() {
        return foodId;
    }

    public int getCount() {
        return count;
    }
}
