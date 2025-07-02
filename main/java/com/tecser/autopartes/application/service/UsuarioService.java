package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.CrearUsuarioConNombresRolDto;
import com.tecser.autopartes.domain.model.*;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.RolJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioRolJpaRepository;

import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioJpaRepository usuarioRepo;
    private final RolJpaRepository rolRepo;
    private final UsuarioRolJpaRepository usuarioRolRepo;

    public UsuarioService(UsuarioJpaRepository usuarioRepo, RolJpaRepository rolRepo, UsuarioRolJpaRepository usuarioRolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.usuarioRolRepo = usuarioRolRepo;
    }

    public Usuario crearUsuarioConRoles(CrearUsuarioConNombresRolDto dto) {
        Usuario usuario = new Usuario(
                dto.getNombreCompleto(),
                dto.getCorreo(),
                dto.getContrasena() // ⚠️ En producción, cifra antes de guardar
        );
        usuarioRepo.save(usuario);

        for (String nombreRol : dto.getNombresRoles()) {
            Rol rol = rolRepo.findByNombre(nombreRol)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
            UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
            usuarioRolRepo.save(usuarioRol);
        }

        return usuario;
    }
}
