package com.exemple.clinique.service.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;

    public String generateToken(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticateUser(authenticationToken);

        return createToken(authentication);
    }

    public Authentication authenticateUser(UsernamePasswordAuthenticationToken authenticationtoken) {
        try
        {
            return  authenticationManager.authenticate(authenticationtoken);
        }
        catch (Exception e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private String createToken(Authentication authentication) {

        Set<String> authorities = authentication.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30)) // 30 Minutes
                .claim("roles", authorities)
                .build();

        JwsHeader jwsHeader =  JwsHeader.with(MacAlgorithm.HS256).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }



}
