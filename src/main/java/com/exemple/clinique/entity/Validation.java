package com.exemple.clinique.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Validation extends AbstractEntity{

    private String code;

    private Instant generatedAt;

    private boolean isValidated = false;

    private Instant expiredAt;

    @Column(nullable = true)
    private Instant validatedAt;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
