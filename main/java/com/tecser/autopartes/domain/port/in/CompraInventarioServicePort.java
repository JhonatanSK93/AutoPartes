package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.CompraInventarioDto;

import java.util.List;

public interface CompraInventarioServicePort {
    CompraInventarioDto guardarCompra(CompraInventarioDto dto);
    List<CompraInventarioDto> listarCompras();
    CompraInventarioDto buscarPorId(Long idCompra);
    void eliminarCompra(Long idCompra);
    CompraInventarioDto actualizarCompra(Long idCompra, CompraInventarioDto dto);
    // Nuevo método
    List<CompraInventarioDto> listarComprasPorUsuario();
}
