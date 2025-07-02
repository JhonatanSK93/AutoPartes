package com.tecser.autopartes.security;

import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private static UsuarioJpaRepository usuarioRepository;

    public SecurityUtils(UsuarioJpaRepository usuarioRepository) {
        SecurityUtils.usuarioRepository = usuarioRepository;
    }

    public static Usuario getUsuarioAutenticado() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con correo: " + correo));
    }

    public static String getCorreoUsuarioAutenticado() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

