package com.exemple.clinique.service.contracts;

import com.exemple.clinique.entity.Utilisateur;
import com.exemple.clinique.entity.Validation;

public interface ValidationService {

    void saveValidation(Utilisateur utilisateur);
    Validation findByCode(String code);
    void setValidatedAt(Validation validation);
}
