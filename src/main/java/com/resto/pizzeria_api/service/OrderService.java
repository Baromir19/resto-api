package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.model.Order;
import com.resto.pizzeria_api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(final Integer id) {
        // todo: .orElseThrow
        return orderRepository.findById(id).orElse(null);
    }
}