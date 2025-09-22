package com.exemple.clinique.service.contracts;

import com.exemple.clinique.dtos.ValidationRequest;
import com.exemple.clinique.dtos.utilisateurs.UtilisateurDto;

import com.exemple.clinique.enums.RoleType;
import com.exemple.clinique.service.AbstractService;

public interface UtilisateurService extends AbstractService<UtilisateurDto> {
        boolean enabledById(Long id);
        boolean disabledById(Long id);
        UtilisateurDto saveMedecin(UtilisateurDto utilisateurDto);
        UtilisateurDto saveSecretaire(UtilisateurDto utilisateurDto);
        UtilisateurDto saveAdmin(UtilisateurDto utilisateurDto);
        void enabledAccount(ValidationRequest request);
}
