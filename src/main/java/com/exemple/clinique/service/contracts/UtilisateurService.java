package com.exemple.clinique.service.contracts;

import com.exemple.clinique.dtos.utilisateurs.PasswordRequest;
import com.exemple.clinique.dtos.validations.ValidationRequest;
import com.exemple.clinique.dtos.utilisateurs.UtilisateurDto;

import com.exemple.clinique.service.AbstractService;

public interface UtilisateurService extends AbstractService<UtilisateurDto> {
        boolean enabledById(Long id);
        boolean disabledById(Long id);

        void enabledAccount(ValidationRequest request);
        void passwordChange(PasswordRequest request);
}
