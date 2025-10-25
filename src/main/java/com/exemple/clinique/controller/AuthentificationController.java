package com.exemple.clinique.controller;

import com.exemple.clinique.dtos.authentication.AuthRequestDto;
import com.exemple.clinique.dtos.jwtToken.JwtToken;
import com.exemple.clinique.service.jwt.JwtTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthentificationController {

    private final JwtTokenService jwtTokenService;

    @PostMapping("/auth")
    public ResponseEntity<JwtToken> auth(@RequestBody AuthRequestDto authRequestDto){
        String token = jwtTokenService.generateToken(authRequestDto.email(), authRequestDto.password());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);

        return  new ResponseEntity<>(new JwtToken(token), httpHeaders, HttpStatus.OK);
    }
}
