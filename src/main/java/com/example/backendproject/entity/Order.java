package com.example.backendproject.entity;

import jakarta.persistence.*;

// 주문 추가
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    private int count;

    protected Order() {}

    public Order(User user, Food food, int count) {
        this.user = user;
        this.food = food;
        this.count = count;
    }

    public Order(Long id, User user, Food food, int count) {
        this.id = id;
        this.user = user;
        this.food = food;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Food getFood() {
        return food;
    }

    public int getCount() {
        return count;
    }
}
