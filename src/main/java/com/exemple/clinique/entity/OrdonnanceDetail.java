package com.exemple.clinique.entity;

import com.exemple.clinique.enums.FormMedicament;
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
@Table(name = "ordonnance_details")
public class OrdonnanceDetail extends AbstractEntity{

    private String dosage;

    private FormMedicament form;

    private String medicament;

    @Lob
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "ordonnance_detail_id")
    private Ordonnance ordonnance;
}
