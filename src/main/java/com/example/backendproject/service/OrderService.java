package com.example.backendproject.service;

import com.example.backendproject.dto.OrderDTO;
import com.example.backendproject.entity.Order;
import com.example.backendproject.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 주문 조회
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(new OrderDTO(order.getId(), order.getName(), order.getCount()));
        }
        return orderDTOs;
    }

    // 주문 추가
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setName(orderDTO.name());
        order.setCount(orderDTO.count());
        Order saveOrder = orderRepository.save(order);
        return new OrderDTO(saveOrder.getId(), saveOrder.getName(), saveOrder.getCount());
    }

    // 주문 변경
    public OrderDTO changeOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
        order.setName(orderDTO.name());
        order.setCount(orderDTO.count());
        Order changeOrder = orderRepository.save(order);
        return new OrderDTO(changeOrder.getId(), changeOrder.getName(), changeOrder.getCount());
    }

    // 주문 취소
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        } else {
            orderRepository.deleteById(id);
        }
    }
}