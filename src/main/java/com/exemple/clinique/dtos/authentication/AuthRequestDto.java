package com.exemple.clinique.dtos.authentication;

public record AuthRequestDto(
        String email,
        String password
) { }
