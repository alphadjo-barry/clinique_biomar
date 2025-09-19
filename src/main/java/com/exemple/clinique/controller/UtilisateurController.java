package com.exemple.clinique.controller;

import com.exemple.clinique.dtos.utilisateurs.UtilisateurDto;
import com.exemple.clinique.service.contracts.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UtilisateurDto utilisateurDto){

        return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
    }
}
