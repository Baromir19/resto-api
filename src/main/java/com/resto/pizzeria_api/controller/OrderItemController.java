package com.resto.pizzeria_api.controller;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.OrderItem;
import com.resto.pizzeria_api.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderItem getOrderItemById(
            @PathVariable final Integer id
    ) throws ApiNotFoundException {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderItem createOrderItem(@RequestBody OrderItem item) {
        return orderItemService.saveOrderItem(item);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderItem updateOrderItem(
            @PathVariable final Integer id,
            @RequestBody final OrderItem updated
    ) throws ApiNotFoundException {
        final OrderItem existing = orderItemService.getOrderItemById(id);

        existing.setQuantity(updated.getQuantity());
        existing.setDish(updated.getDish());

        return orderItemService.saveOrderItem(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) throws ApiNotFoundException {
        orderItemService.getOrderItemById(id); // vérifier l'existence

        orderItemService.deleteOrderItem(id);
    }
}