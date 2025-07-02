package com.tecser.autopartes.security;

import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.UsuarioRol;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioRolJpaRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioJpaRepository usuarioRepo;
    private final UsuarioRolJpaRepository usuarioRolRepo;

    public UsuarioDetailsService(UsuarioJpaRepository usuarioRepo, UsuarioRolJpaRepository usuarioRolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.usuarioRolRepo = usuarioRolRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        System.out.println("📌 Usuario encontrado en BD:");
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Contraseña cifrada en DB: " + usuario.getContrasena());

        Optional<UsuarioRol> roles = usuarioRolRepo.findByUsuarioId(usuario.getId());
        List<GrantedAuthority> authorities = roles.stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRol().getNombre())) 
                .collect(Collectors.toList());

        return new User(usuario.getCorreo(), usuario.getContrasena(), authorities);
    }
}
