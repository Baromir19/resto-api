package com.resto.pizzeria_api.controller;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.Order;
import com.resto.pizzeria_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(
            @PathVariable final Integer id
    ) throws ApiNotFoundException {
        return orderService.getOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Order createOrder(@RequestBody final Order order) {
        order.setCreationDate(LocalDateTime.now());

        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }

        return orderService.saveOrder(order);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(
            @PathVariable final Integer id,
            @RequestBody final Order updatedOrder
    ) throws ApiNotFoundException {
        final Order existing = orderService.getOrderById(id);

        existing.setDailyId(updatedOrder.getDailyId());
        existing.setStatus(updatedOrder.getStatus());
        existing.setClient(updatedOrder.getClient());

        existing.getItems().clear();

        if (updatedOrder.getItems() != null) {
            updatedOrder.getItems().forEach(item -> {
                item.setOrder(existing);
                existing.getItems().add(item);
            });
        }

        return orderService.saveOrder(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(
            @PathVariable final Integer id
    ) throws ApiNotFoundException {
        orderService.getOrderById(id); // vérifier l'existence
        orderService.deleteOrder(id);
    }
}