package com.exemple.clinique.service.impl;

import com.exemple.clinique.dtos.roles.RoleDto;

import com.exemple.clinique.entity.Role;
import com.exemple.clinique.repository.contracts.RoleRepository;
import com.exemple.clinique.service.contracts.RoleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        return this.roleRepository.findAll().stream()
                .map(RoleDto::fromEntity)
                .toList();
    }

    @Override
    public RoleDto findById(Long id) {

        Role role = this.roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role not found !")
        );

        return RoleDto.fromEntity(role);
    }

    @Override
    public RoleDto save(RoleDto roleDto) {

        if(roleRepository.findByName(roleDto.getName()).isPresent()){
            throw new EntityExistsException("Role already exists");
        }

        Role newRole = RoleDto.toEntity(roleDto);
        Role savedRole = this.roleRepository.save(newRole);

        return RoleDto.fromEntity(savedRole);
    }

    @Override
    public void delete(Long id) {
        if(roleRepository.findById(id).isPresent()){
            throw new EntityNotFoundException("Role not found !");
        }

        this.roleRepository.deleteById(id);
    }

}
