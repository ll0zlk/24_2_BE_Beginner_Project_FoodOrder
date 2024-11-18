package com.example.backendproject.dto;

// 데이터 전달
public record OrderDTO(Long id, String userId, String foodId, int count) {
}