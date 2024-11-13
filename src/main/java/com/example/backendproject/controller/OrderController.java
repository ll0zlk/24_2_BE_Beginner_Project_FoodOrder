package com.example.backendproject.controller;

import com.example.backendproject.dto.OrderDTO;
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

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.getOrders();
    }

    // 주문 추가
    @PostMapping
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    // 주문 변경
    @PutMapping("/{id}")
    public OrderDTO changeOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return orderService.changeOrder(id, orderDTO);
    }

    // 주문 취소
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
