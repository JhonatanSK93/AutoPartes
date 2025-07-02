package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.UsuarioRolDto;
import com.tecser.autopartes.application.service.UsuarioRolService;
import com.tecser.autopartes.domain.model.UsuarioRol;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

/**
 * Controlador REST para la gestión de asignación de roles a usuarios.
 * Permite asignar, obtener y eliminar roles de usuarios.
 */
@RestController
@RequestMapping("/usuarios/roles")
public class UsuarioRolController {

    private final UsuarioRolService usuarioRolService;

    public UsuarioRolController(UsuarioRolService usuarioRolService) {
        this.usuarioRolService = usuarioRolService;
    }

    /**
     * ✅ Asigna un rol a un usuario.
     *
     * @param dto Objeto que contiene los IDs de usuario y rol.
     * @return UsuarioRol creado.
     */
    @PostMapping("/asignar")
    public ResponseEntity<UsuarioRol> asignarRol(@RequestBody @Valid UsuarioRolDto dto) {
        UsuarioRol usuarioRol = usuarioRolService.asignarRol(dto.getUsuarioId(), dto.getRolId());
        return ResponseEntity.ok(usuarioRol);
    }

    /**
     * ✅ Obtiene todos los roles asignados a un usuario.
     *
     * @param usuarioId ID del usuario.
     * @return Lista de relaciones UsuarioRol.
     */
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Optional<UsuarioRol>> obtenerRoles(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usuarioRolService.obtenerRolesPorUsuario(usuarioId));
    }

    /**
     * ✅ Elimina un rol específico de un usuario.
     *
     * @param dto Objeto con el ID del usuario y el rol a eliminar.
     * @return No Content si fue exitoso.
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarRol(@RequestBody @Valid UsuarioRolDto dto) {
        usuarioRolService.eliminarRolDeUsuario(dto.getUsuarioId(), dto.getRolId());
        return ResponseEntity.noContent().build();
    }
}
