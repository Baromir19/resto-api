package com.resto.pizzeria_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer id;

    @Column(name = "daily_id_order", nullable = false)
    private Integer dailyId;

    @Column(name = "date_creation_order", nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
}