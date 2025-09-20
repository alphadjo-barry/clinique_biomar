package com.exemple.clinique.controller;

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

    @PatchMapping("/enable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> enableById(@RequestBody Long id){
        return ResponseEntity.ok(utilisateurService.enabledById(id));
    }

    @PatchMapping("/disable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> disableById(@RequestBody Long id){
        return ResponseEntity.ok(utilisateurService.disabledById(id));
    }



}
