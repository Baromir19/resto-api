package com.resto.pizzeria_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entité représentant un article de commande.
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    /** Identifiant de l'article. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item")
    private Integer id;

    /** Commande associée. */
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    @JsonBackReference
    private Order order;

    /** Plat associé. */
    @ManyToOne
    @JoinColumn(name = "id_dish", nullable = false)
    private Dish dish;

    /** Quantité commandée. */
    @Column(name = "quantity_order_item", nullable = false)
    private Integer quantity;
}