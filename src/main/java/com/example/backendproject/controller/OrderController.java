package com.example.backendproject.controller;

import com.example.backendproject.dto.OrderRequestDTO;
import com.example.backendproject.dto.OrderResponseDTO;
import com.example.backendproject.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 주문 조회
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 조회
    @GetMapping
    public List<OrderResponseDTO> getOrders() {
        return orderService.getOrders();
    }

    // 주문 추가
    @PostMapping
    public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.addOrder(orderRequestDTO);
    }

    // 주문 변경
    @PutMapping("/{id}")
    public OrderResponseDTO changeOrder(@PathVariable Long id, @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.changeOrder(id, orderRequestDTO);
    }

    // 주문 취소
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
