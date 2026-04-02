package com.resto.pizzeria_api.model;

import jakarta.persistence.*;

// todo:
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dish")
    private Integer id;
}
