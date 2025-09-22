package com.exemple.clinique.dtos.patients;

import com.exemple.clinique.dtos.adresses.AdresseDto;
import com.exemple.clinique.dtos.consultations.ConsultationDto;
import com.exemple.clinique.dtos.rdvs.RdvDto;

import com.exemple.clinique.entity.Adresse;
import com.exemple.clinique.entity.Consultation;
import com.exemple.clinique.entity.Patient;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
public class PatientDto {

    private Long id;

    private Long codeIdentifiantPatient;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Sexe is required")
    private String sexe;
    @NotBlank(message = "Date of birth is required")
    private Date dateOfBirth;
    @NotBlank(message = "No Securite Social is required")
    private String noSecuriteSocial;
    @NotBlank(message = "Phone is required")
    private String phone;

    List<RdvDto> rdvs;
    List<AdresseDto> adresses;
    List<ConsultationDto> consultations;

    public static Patient toEntity(Patient patient){

        return Patient.builder()
                .codeIdentifiantPatient(patient.getCodeIdentifiantPatient())
                .lastName(patient.getLastName())
                .firstName(patient.getFirstName())
                .sexe(patient.getSexe())
                .dateOfBirth(patient.getDateOfBirth())
                .noSecuriteSocial(patient.getNoSecuriteSocial())
                .phone(patient.getPhone())
                .build();
    }

    public static PatientDto fromEntity(Patient patient){

        return PatientDto.builder()
                .id(patient.getId())
                .codeIdentifiantPatient(patient.getCodeIdentifiantPatient())
                .lastName(patient.getLastName())
                .firstName(patient.getFirstName())
                .sexe(patient.getSexe())
                .dateOfBirth(patient.getDateOfBirth())
                .noSecuriteSocial(patient.getNoSecuriteSocial())
                .phone(patient.getPhone())
//              .rdvs(
//                      patient.getRdvs() != null ? patient.getRdvs().stream()
//                              .map(RdvDto::fromEntity)
//                              .collect(Collectors.toList()): new ArrayList<>())
                .adresses(
                        patient.getAdresses() != null ? patient.getAdresses().stream()
                                .filter(Adresse::isMain)
                                .map(AdresseDto::fromEntity)
                                .collect(Collectors.toList()): new ArrayList<>()
                )
                .build();
    }
}
