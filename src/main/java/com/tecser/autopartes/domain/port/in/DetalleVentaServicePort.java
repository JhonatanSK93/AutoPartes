package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.DetalleVentaDto;

import java.util.List;

public interface DetalleVentaServicePort {

    DetalleVentaDto guardar(DetalleVentaDto dto);

    List<DetalleVentaDto> listar();

    DetalleVentaDto obtenerPorId(Long id);

    void eliminar(Long id);
}
