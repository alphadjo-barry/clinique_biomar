package com.exemple.clinique.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "medecins")
public class Medecin extends AbstractEntity{

    private String lastName;

    private String firstName;

    @ManyToMany
    @JoinTable(name = "medecin_adresses", joinColumns = @JoinColumn(name = "medecin_id"),
    inverseJoinColumns = @JoinColumn(name="adresse_id"))
    private List<Adresse> adresses;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "medecin")
    List<Rdv> rdvs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "medecin_disponibilites",
    joinColumns = @JoinColumn(name = "medecin_id"),
    inverseJoinColumns = @JoinColumn(name = "disponibilite_id"))
    private Set<Disponibilite> disponibilites = new HashSet<>();
}
