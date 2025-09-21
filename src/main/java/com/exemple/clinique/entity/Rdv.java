package com.exemple.clinique.entity;

import com.exemple.clinique.enums.RdvType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "rdvs")
public class Rdv extends AbstractEntity{

    @Temporal(TemporalType.DATE)
    private Date date;

    private LocalTime startHour;

    private LocalTime startEnd;

    private RdvType rdvType;
    private RdvStatut rdvStatut;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
}
