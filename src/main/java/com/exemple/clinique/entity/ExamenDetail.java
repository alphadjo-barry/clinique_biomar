package com.exemple.clinique.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "examen_details")
public class ExamenDetail extends AbstractEntity{

    private String intituleExamen;

    @Column(nullable = true)
    private String resultat;

    @Column(nullable = true)
    private String remarque;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;
}
