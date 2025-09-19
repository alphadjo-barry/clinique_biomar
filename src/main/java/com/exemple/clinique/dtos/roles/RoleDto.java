package com.exemple.clinique.dtos.roles;

import com.exemple.clinique.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class RoleDto {

    private long id;

    private String name;

    public static Role toEntity(RoleDto roleDto){

        return Role.builder()
                .name(roleDto.name)
                .build();
    }

    public static RoleDto fromEntity(Role role){

        return RoleDto.builder()
                .id(role.getId())
                .name("ROLE"+role.getName())
                .build();
    }
}
