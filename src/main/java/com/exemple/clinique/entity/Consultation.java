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
@Table(name = "consultations")
public class Consultation extends AbstractEntity{

    @Lob
    private String plaintes;

    @ManyToOne
    @JoinColumn(name = "rdv_id")
    private Rdv rdv;
}
