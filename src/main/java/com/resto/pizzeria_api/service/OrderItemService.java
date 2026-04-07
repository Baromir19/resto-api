package com.resto.pizzeria_api.service;

import com.resto.pizzeria_api.exception.ApiNotFoundException;
import com.resto.pizzeria_api.model.OrderItem;
import com.resto.pizzeria_api.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository orderItemRepository;

  public List<OrderItem> getAllOrderItems() {
    return orderItemRepository.findAll();
  }

  public OrderItem getOrderItemById(
      final Integer id
  ) throws ApiNotFoundException {
    return orderItemRepository.findById(id)
        .orElseThrow(() -> new ApiNotFoundException(
            "Article de commande n'a pas été trouvé"));
  }

  public OrderItem saveOrderItem(final OrderItem orderItem) {
    return orderItemRepository.save(orderItem);
  }

  public void deleteOrderItem(final Integer id) throws ApiNotFoundException {
    if (!orderItemRepository.existsById(id)) {
      throw new ApiNotFoundException(
          "Article de commande n'a pas été trouvé");
    }

    orderItemRepository.deleteById(id);
  }
}
