package com.resto.pizzeria_api.repository;

import com.resto.pizzeria_api.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour gérer les plats.
 * Étend JpaRepository pour fournir les opérations CRUD standard.
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}
