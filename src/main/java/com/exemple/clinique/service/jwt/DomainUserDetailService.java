package com.exemple.clinique.service.jwt;

import com.exemple.clinique.entity.Utilisateur;
import com.exemple.clinique.repository.contracts.UtilisateurRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DomainUserDetailService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<SimpleGrantedAuthority> authorities = getAuthorities(utilisateur);

        return new User(utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.isEnabled(), utilisateur.isEnabled(), utilisateur.isEnabled(), utilisateur.isEnabled(), authorities);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Utilisateur utilisateur) {
        return utilisateur.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
    }
}
