package com.example.backendproject.entity;

import jakarta.persistence.*;

// 주문 추가
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int count;

    // 신규 주문
    public Order(String name, int count) {
        this.name = name;
        this.count = count;
    }

    // 기존 주문
    public Order(Long id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    protected Order() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
