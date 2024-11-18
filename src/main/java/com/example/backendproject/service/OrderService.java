package com.example.backendproject.service;

import com.example.backendproject.dto.OrderDTO;
import com.example.backendproject.entity.Food;
import com.example.backendproject.entity.Order;
import com.example.backendproject.entity.User;
import com.example.backendproject.repository.FoodRepository;
import com.example.backendproject.repository.OrderRepository;
import com.example.backendproject.repository.UserRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(new OrderDTO(order.getId(), order.getUser().getId(), order.getFood().getId(), order.getCount()));
        }
        return orderDTOs;
    }

    // 주문 추가
    public OrderDTO addOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Food food = foodRepository.findById(orderDTO.foodId()).orElseThrow(() -> new RuntimeException("Food not found"));

        Order order = new Order(user, food, orderDTO.count());
        Order saveOrder = orderRepository.save(order);
        return new OrderDTO(saveOrder.getId(), saveOrder.getUser().getId(), saveOrder.getFood().getId(), saveOrder.getCount());
    }

    // 주문 변경
    public OrderDTO changeOrder(Long id, OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        Food food = foodRepository.findById(orderDTO.foodId()).orElseThrow(() -> new RuntimeException("Food not found"));
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));

        Order changeOrder = new Order(order.getId(), user, food, orderDTO.count());
        Order saveOrder = orderRepository.save(changeOrder);
        return new OrderDTO(saveOrder.getId(), saveOrder.getUser().getId(), saveOrder.getFood().getId(), saveOrder.getCount());
    }

    // 주문 취소
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}