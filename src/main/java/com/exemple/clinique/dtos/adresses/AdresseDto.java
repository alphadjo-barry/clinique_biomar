package com.exemple.clinique.dtos.adresses;

import com.exemple.clinique.entity.Adresse;
import com.exemple.clinique.entity.Patient;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AdresseDto {

    private Long id;
    @NotBlank(message = "Rue is required")
    private  String rue;
    @NotBlank(message = "Code postal is required")
    private String codePostal;
    @NotBlank(message = "Ville is required")
    private String ville;
    @NotBlank(message = "Pays is required")
    private String pays;
    @NotBlank(message = "Patient is required")
    private Patient patient;

    public static Adresse toEntity(AdresseDto adresseDto){

        return Adresse.builder()
                .rue(adresseDto.rue)
                .codePostal(adresseDto.codePostal)
                .ville(adresseDto.ville)
                .pays(adresseDto.pays)
                .patient(adresseDto.patient)
                .build();
    }

    public static AdresseDto fromEntity(Adresse adresse){

        return AdresseDto.builder()
                .id(adresse.getId())
                .rue(adresse.getRue())
                .codePostal(adresse.getCodePostal())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .patient(adresse.getPatient())
                .build();
    }


}
