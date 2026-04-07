package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
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

  public Order getOrderById(
      final Integer id
  ) throws ApiNotFoundException {
    return orderRepository.findById(id)
        .orElseThrow(() -> new ApiNotFoundException(
            "Commande n'a pas été trouvée"));
  }

  public Order saveOrder(final Order order) {
    return orderRepository.save(order);
  }

  public void deleteOrder(final Integer id) throws ApiNotFoundException {
    if (!orderRepository.existsById(id)) {
      throw new ApiNotFoundException(
          "Commande n'a pas été trouvée");
    }

    orderRepository.deleteById(id);
  }
}