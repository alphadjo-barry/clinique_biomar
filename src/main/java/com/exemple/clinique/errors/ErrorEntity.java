package com.exemple.clinique.errors;

import lombok.Builder;

@Builder
public record ErrorEntity(
        String code,
        String message
) { }
