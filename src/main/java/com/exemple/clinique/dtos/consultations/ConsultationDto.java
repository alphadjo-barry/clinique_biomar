package com.exemple.clinique.dtos.consultations;

import com.exemple.clinique.entity.Consultation;
import com.exemple.clinique.entity.Patient;

import jakarta.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ConsultationDto {

    private Long id;

    @Lob
    private String plaintes;

    private Patient patient;

    public static Consultation toEntity(ConsultationDto consultationDto){
        return com.exemple.clinique.entity.Consultation.builder()
                .plaintes(consultationDto.plaintes)
                .patient(consultationDto.patient)
                .build();
    }

    public static ConsultationDto fromEntity(com.exemple.clinique.entity.Consultation consultation){

        return ConsultationDto.builder()
                .id(consultation.getId())
                .plaintes(consultation.getPlaintes())
                .patient(consultation.getPatient())
                .build();
    }

}
