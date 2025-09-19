package com.exemple.clinique.service.impl;

import com.exemple.clinique.dtos.utilisateurs.UtilisateurDto;
import com.exemple.clinique.entity.Role;
import com.exemple.clinique.entity.Utilisateur;
import com.exemple.clinique.enums.RoleType;
import com.exemple.clinique.repository.contracts.RoleRepository;
import com.exemple.clinique.repository.contracts.UtilisateurRepository;
import com.exemple.clinique.service.contracts.UtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<UtilisateurDto> findAll() {
        return this.utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .toList();
    }

    @Override
    public UtilisateurDto findById(Long id) {
        return this.utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Utilisateur not found !"));
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        if(utilisateurRepository.findByEmail(utilisateurDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Utilisateur already exists");
        }

        Utilisateur utilisateur = UtilisateurDto.toEntity(utilisateurDto);
        utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateurDto.getPassword()));

        RoleType roleType;

        if(utilisateurRepository.count() == 0)
            roleType = RoleType.ADMIN;
        else roleType = RoleType.PATIENT;

        Role role = roleRepository.findByName(roleType.name()).orElseGet(
                () -> roleRepository.save(Role.builder().name(roleType.name()).build()));

        if(utilisateur.getRoles() == null)
            utilisateur.setRoles(new HashSet<>());

        utilisateur.getRoles().add(role);

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);

        return UtilisateurDto.fromEntity(savedUtilisateur);
    }

    @Override
    public void delete(Long id) {

        if(utilisateurRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Utilisateur not found !");
        }

        utilisateurRepository.deleteById(id);
    }


}
