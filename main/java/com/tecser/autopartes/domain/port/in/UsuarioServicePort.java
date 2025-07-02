package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.UsuarioRolDto;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicePort {

    UsuarioRolDto crearUsuario(UsuarioRolDto dto);

    List<UsuarioRolDto> listarUsuarios();

    Optional<UsuarioRolDto> buscarPorId(Long id);

    void eliminarUsuario(Long id);
}
