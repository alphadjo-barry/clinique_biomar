package com.exemple.clinique.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "patients")
public class Patient extends AbstractEntity{

    private String lastName;
    private String firstName;
    private String address;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String noSecuriteSocial;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "patient")
    List<Rdv> rdvs = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    List<Consultation> consultations = new ArrayList<>();
}
