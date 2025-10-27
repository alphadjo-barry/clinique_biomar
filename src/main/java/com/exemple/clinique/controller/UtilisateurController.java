package com.exemple.clinique.controller;

import com.exemple.clinique.dtos.utilisateurs.PasswordRequest;
import com.exemple.clinique.dtos.validations.ValidationRequest;
import com.exemple.clinique.dtos.utilisateurs.UtilisateurDto;
import com.exemple.clinique.service.contracts.UtilisateurService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UtilisateurDto utilisateurDto){

        return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Long id){
        utilisateurService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UtilisateurDto utilisateurDto){
        return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
    }

    @PatchMapping("/activation")
    public ResponseEntity<?> enableAccount(@RequestBody ValidationRequest code){
        utilisateurService.enabledAccount(code);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/enable")
    public ResponseEntity<?> enabledById(@RequestBody Long id){
        return ResponseEntity.ok(utilisateurService.enabledById(id));
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @PatchMapping("/disable")
    public ResponseEntity<?> disableById(@RequestBody Long id){
        return ResponseEntity.ok(utilisateurService.disabledById(id));
    }

    @PatchMapping("/password")
    public ResponseEntity<?> passwordChange(@RequestBody PasswordRequest passwordRequest){

          return ResponseEntity.ok(utilisateurService.passwordChange(passwordRequest));
    }
}
