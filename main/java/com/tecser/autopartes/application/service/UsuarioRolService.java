package com.tecser.autopartes.application.service;

import com.tecser.autopartes.adapter.out.persistence.repository.RolJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.UsuarioRolJpaRepository;
import com.tecser.autopartes.domain.model.Rol;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.UsuarioRol;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UsuarioRolService {

    private final UsuarioRolJpaRepository usuarioRolRepo;
    private final UsuarioJpaRepository usuarioRepo;
    private final RolJpaRepository rolRepo;

    public UsuarioRolService(
            UsuarioRolJpaRepository usuarioRolRepo,
            UsuarioJpaRepository usuarioRepo,
            RolJpaRepository rolRepo
    ) {
        this.usuarioRolRepo = usuarioRolRepo;
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
    }

    public UsuarioRol asignarRol(Long usuarioId, Long rolId) {
        if (usuarioRolRepo.existsByUsuarioIdAndRolId(usuarioId, rolId)) {
            throw new RuntimeException("El usuario ya tiene este rol asignado.");
        }

        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepo.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
        return usuarioRolRepo.save(usuarioRol);
    }

    public Optional<UsuarioRol> obtenerRolesPorUsuario(Long usuarioId) {
        return usuarioRolRepo.findByUsuarioId(usuarioId);
    }

    public void eliminarRolDeUsuario(Long usuarioId, Long rolId) {
        usuarioRolRepo.findById(new com.tecser.autopartes.domain.model.UsuarioRolId(usuarioId, rolId))
                .ifPresent(usuarioRolRepo::delete);
    }
}
