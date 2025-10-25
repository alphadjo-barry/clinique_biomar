package com.exemple.clinique.dtos.consultations;

import com.exemple.clinique.entity.Consultation;
import com.exemple.clinique.entity.Rdv;
import jakarta.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ConsultationDto {

    private Long id;
    @Lob
    private String plaintes;
    private Rdv rdv;

    public static Consultation toEntity(ConsultationDto consultationDto){
        return Consultation.builder()
                .plaintes(consultationDto.plaintes)
                .rdv(consultationDto.rdv)
                .build();
    }

    public static ConsultationDto fromEntity(Consultation consultation){

        return ConsultationDto.builder()
                .id(consultation.getId())
                .plaintes(consultation.getPlaintes())
                .rdv(consultation.getRdv())
                .build();
    }
}
