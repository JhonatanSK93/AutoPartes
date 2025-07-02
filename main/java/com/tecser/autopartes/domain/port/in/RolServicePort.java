package com.tecser.autopartes.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.tecser.autopartes.application.dto.RolDto;

public interface RolServicePort {
    RolDto crearRol(RolDto rolDto);
    List<RolDto> listarRoles();
    Optional<RolDto> buscarPorNombre(String nombre);
}