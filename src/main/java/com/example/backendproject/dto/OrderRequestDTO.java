package com.example.backendproject.dto;

public record OrderRequestDTO(Long userId, Long foodId, int count) {
}