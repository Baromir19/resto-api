package com.resto.pizzeria_api.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entité représentant un statut de commande.
 */
@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    /** Identifiant du statut. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer id;

    /** Libellé du statut. */
    @Column(name = "label_status", nullable = false,
            unique = true, length = 50)
    private String label;
}
