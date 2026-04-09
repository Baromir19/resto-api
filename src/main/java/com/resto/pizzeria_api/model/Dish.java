package com.resto.pizzeria_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  /**
   * Identifiant du plat.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_dish")
  private Integer id;

  /**
   * Nom du plat.
   */
  @Column(name = "name_dish", nullable = false, length = 50)
  private String name;

  @Column(name = "category_dish", length = 50)
  private String category;

  /**
   * Prix du plat.
   */
  @Column(name = "price_dish", nullable = false, precision = 15, scale = 2)
  private BigDecimal price;

  /**
   * Description du plat.
   */
  @Column(name = "description_dish", nullable = false, length = 250)
  private String description;

  // columnDefinition permet d'injecter la valeur par défaut directement dans le SQL généré
  @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
  private Boolean available = true;
}