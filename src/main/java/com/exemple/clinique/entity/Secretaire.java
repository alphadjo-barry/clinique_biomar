package com.exemple.clinique.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "secretaires")
public class Secretaire extends AbstractEntity{

    private String lastName;
    private String firstName;
    private String address;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;
}
