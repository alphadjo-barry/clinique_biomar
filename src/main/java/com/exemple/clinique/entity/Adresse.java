package com.exemple.clinique.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Adresse extends AbstractEntity{
    String rue;
    String codePostal;
    String ville;
    String pays;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
