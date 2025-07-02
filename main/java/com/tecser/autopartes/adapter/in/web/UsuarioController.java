package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.CrearUsuarioConNombresRolDto;
import com.tecser.autopartes.application.service.UsuarioService;
import com.tecser.autopartes.domain.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ✅ Crear usuario con roles
    @PostMapping("/crear-con-roles")
    public ResponseEntity<Usuario> crearUsuarioConRoles(@RequestBody CrearUsuarioConNombresRolDto dto) {
        Usuario usuarioCreado = usuarioService.crearUsuarioConRoles(dto);
        return ResponseEntity.ok(usuarioCreado);
    }
}
