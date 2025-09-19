package com.exemple.clinique.dtos.utilisateurs;

import com.exemple.clinique.dtos.roles.RoleDto;
import com.exemple.clinique.entity.Utilisateur;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class UtilisateurDto{

    private Long id;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    private Set<RoleDto> roles;

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){

        return Utilisateur.builder()
                .email(utilisateurDto.getEmail())
                .password(utilisateurDto.getPassword())
                .build();
    }

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .roles(
                        utilisateur.getRoles() != null ? utilisateur.getRoles().stream()
                                .map(RoleDto::fromEntity)
                                .collect(Collectors.toSet()) : new HashSet<>()
                )
                .build();
    }
}
