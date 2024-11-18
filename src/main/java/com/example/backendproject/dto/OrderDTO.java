package com.example.backendproject.dto;

// 데이터 전달
public record OrderDTO(Long id, Long userId, Long foodId, int count) {
}