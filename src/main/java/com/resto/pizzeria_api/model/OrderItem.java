package com.resto.pizzeria_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    @JsonBackReference // problème de récursivité
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_dish", nullable = false)
    private Dish dish;

    @Column(name = "quantity_order_item", nullable = false)
    private Integer quantity;
}