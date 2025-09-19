package com.exemple.clinique.dtos;

public record AuthRequestDto(
        String email,
        String password
) { }
