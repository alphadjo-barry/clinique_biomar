package com.exemple.clinique.dtos.utilisateurs;

import com.exemple.clinique.dtos.roles.RoleDto;
import com.exemple.clinique.entity.Utilisateur;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial."
    )
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
