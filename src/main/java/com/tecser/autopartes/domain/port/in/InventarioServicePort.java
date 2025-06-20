package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.InventarioDto;

import java.util.List;

public interface InventarioServicePort {
    InventarioDto guardarInventario(InventarioDto dto);
    List<InventarioDto> listarInventarios();
    InventarioDto obtenerInventarioPorId(Long id);
    void eliminarInventario(Long id);
}
