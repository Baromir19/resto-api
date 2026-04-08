package com.resto.pizzeria_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entité représentant un plat.
 */
@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    /** Identifiant du plat. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dish")
    private Integer id;

    /** Nom du plat. */
    @Column(name = "name_dish", nullable = false, length = 50)
    private String name;

    /** Prix du plat. */
    @Column(name = "price_dish", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    /** Description du plat. */
    @Column(name = "description_dish", nullable = false, length = 250)
    private String description;
}