package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.IngresoInventarioDto;

import java.util.List;
import java.util.Optional;

public interface IngresoInventarioServicePort {
    IngresoInventarioDto guardar(IngresoInventarioDto dto);
    List<IngresoInventarioDto> listar();
    Optional<IngresoInventarioDto> buscarPorId(Long id);
    void eliminarIngreso(Long id);
}
