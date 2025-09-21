package com.exemple.clinique.dtos.rdvs;

import com.exemple.clinique.entity.Medecin;
import com.exemple.clinique.entity.Patient;
import com.exemple.clinique.entity.Rdv;
import com.exemple.clinique.entity.RdvStatut;
import com.exemple.clinique.enums.RdvType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalTime;
import java.util.Date;

@Builder
@AllArgsConstructor
public class RdvDto {

    private Long id;

    private Date date;

    private LocalTime startHour;

    private LocalTime startEnd;

    private RdvType rdvType;
    private RdvStatut rdvStatut;

    private Patient patient;

    private Medecin medecin;

    public static Rdv toEntity(RdvDto rdvDto){

        return Rdv.builder()
                .date(rdvDto.date)
                .startHour(rdvDto.startHour)
                .startEnd(rdvDto.startEnd)
                .rdvType(rdvDto.rdvType)
                .rdvStatut(rdvDto.rdvStatut)
                .patient(rdvDto.patient)
                .medecin(rdvDto.medecin)
                .build();
    }

    public static RdvDto fromEntity(Rdv rdv){

        return RdvDto.builder()
                .id(rdv.getId())
                .date(rdv.getDate())
                .startHour(rdv.getStartHour())
                .startEnd(rdv.getStartEnd())
                .rdvType(rdv.getRdvType())
                .rdvStatut(rdv.getRdvStatut())
                .patient(rdv.getPatient())
                .medecin(rdv.getMedecin())
                .build();
    }
}
