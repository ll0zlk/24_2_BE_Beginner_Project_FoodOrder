package com.example.backendproject.service;

import com.example.backendproject.dto.OrderRequestDTO;
import com.example.backendproject.dto.OrderResponseDTO;
import com.example.backendproject.entity.Food;
import com.example.backendproject.entity.Order;
import com.example.backendproject.entity.User;
import com.example.backendproject.repository.FoodRepository;
import com.example.backendproject.repository.OrderRepository;
import com.example.backendproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    // 주문 조회
    @Transactional
    public List<OrderResponseDTO> getOrdersByUserId(String userId) {
        Long userIdLong = Long.valueOf(userId);
        List<Order> orders = orderRepository.findByUserId(userIdLong);
        List<OrderResponseDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(new OrderResponseDTO(order.getId(), order.getUser().getId(), order.getFood().getId(), order.getCount()));
        }
        return orderDTOs;
    }

    // 주문 추가
    @Transactional
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Food food = foodRepository.findById(orderRequestDTO.foodId()).orElseThrow(() -> new RuntimeException("Food not found"));

        Order order = new Order(user, food, orderRequestDTO.count());
        Order saveOrder = orderRepository.save(order);
        return new OrderResponseDTO(saveOrder.getId(), saveOrder.getUser().getId(), saveOrder.getFood().getId(), saveOrder.getCount());
    }

    // 주문 변경
    @Transactional
    public OrderResponseDTO changeOrder(Long id, OrderRequestDTO orderRequestDTO) {
        User user = userRepository.findById(orderRequestDTO.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Food food = foodRepository.findById(orderRequestDTO.foodId()).orElseThrow(() -> new RuntimeException("Food not found"));
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));

        Order changeOrder = new Order(order.getId(), user, food, orderRequestDTO.count());
        Order saveOrder = orderRepository.save(changeOrder);
        return new OrderResponseDTO(saveOrder.getId(), saveOrder.getUser().getId(), saveOrder.getFood().getId(), saveOrder.getCount());
    }

    // 주문 취소
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}