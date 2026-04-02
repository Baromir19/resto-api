package com.resto.pizzeria_api.model;

import jakarta.persistence.*;

// todo:
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer id;
}
