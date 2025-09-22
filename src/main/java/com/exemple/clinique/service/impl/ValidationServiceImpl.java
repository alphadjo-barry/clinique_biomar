package com.exemple.clinique.service.impl;

import com.exemple.clinique.entity.Utilisateur;
import com.exemple.clinique.entity.Validation;
import com.exemple.clinique.repository.contracts.ValidationRepository;
import com.exemple.clinique.service.contracts.ValidationService;
import com.exemple.clinique.service.mail.SendValidationNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@AllArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final SendValidationNotificationService sendValidationNotificationService;
    private final ValidationRepository validationRepository;

    public void saveValidation(Utilisateur utilisateur){

        Validation validation = new Validation();

        validation.setUtilisateur(utilisateur);

        Instant generatedAt = Instant.now();
        Instant expiredAt = generatedAt.plus(10, ChronoUnit.MINUTES);

        validation.setGeneratedAt(generatedAt);
        validation.setExpiredAt(expiredAt);

        Random random = new Random();
        int code = random.nextInt(999999);

        validation.setCode(String.format("%06d", code));

        validationRepository.save(validation);

        sendValidationNotificationService.sendValidationCode(validation);
    }

    public Validation findByCode(String code){
        return validationRepository.findByCode(code)
                .orElseThrow(()-> new IllegalArgumentException("Validation code not found"));
    }

    public void setValidatedAt(Validation validation){
        validation.setValidatedAt(Instant.now());
        validationRepository.save(validation);
    }
}
