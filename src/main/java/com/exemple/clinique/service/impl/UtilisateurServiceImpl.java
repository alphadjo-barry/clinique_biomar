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
    public void update(Long id, UtilisateurDto utilisateurDto) {

        Utilisateur utilisateur = this.utilisateurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Utilisateur not found !")
        );

        Utilisateur other =  this.utilisateurRepository.findByEmail(utilisateurDto.getEmail()).orElse(null);
        if(other != null && !other.getId().equals(id)){
            throw new IllegalArgumentException("Utilisateur already exists");
        }

        utilisateur.setEmail(utilisateurDto.getEmail());

        this.utilisateurRepository.save(utilisateur);
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


        Role role = roleRepository.findByName(RoleType.SECRETARY.name()).orElseGet(
                () -> roleRepository.save(Role.builder().name(RoleType.SECRETARY.name()).build()));

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

    @Override
    public boolean enabledById(Long id) {

        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Utilisateur not found !"));

        if(utilisateur.isEnabled()){
            throw new IllegalArgumentException("Utilisateur is already enabled");
        }
        utilisateur.setActive(true);

        return this.utilisateurRepository.save(utilisateur).isActive();
    }

    @Override
    public boolean disabledById(Long id) {

        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Utilisateur not found !"));

        if(!utilisateur.isEnabled()){
            throw new IllegalArgumentException("Utilisateur is already disabled");
        }

        utilisateur.setActive(false);

        return this.utilisateurRepository.save(utilisateur).isActive();

    }



}
