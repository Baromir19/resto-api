package com.resto.pizzeria_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dish")
    private Integer id;

    @Column(name = "name_dish", nullable = false)
    private String name;

    @Column(name = "price_dish", nullable = false)
    private Double price;

    @Column(name = "description_dish", nullable = false)
    private String description;
}