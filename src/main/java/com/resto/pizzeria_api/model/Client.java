package com.resto.pizzeria_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// todo:
@Entity
@Table(name = "clients")
@Getter // Remplace @Data pour éviter les bugs JPA
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_client")
  private Integer id;

  @Column(name = "first_name_client", nullable = false, length = 50)
  private String firstName;

  @Column(name = "last_name_client", nullable = false, length = 50)
  private String lastName;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Order> orders;
}
