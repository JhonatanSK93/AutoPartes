// DetalleCompraInventarioServicePort.java
package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.DetalleCompraInventarioDto;

import java.util.List;
import java.util.Optional;

public interface DetalleCompraInventarioServicePort {
    DetalleCompraInventarioDto guardar(DetalleCompraInventarioDto dto);
    Optional<DetalleCompraInventarioDto> buscarPorId(Long id);
    List<DetalleCompraInventarioDto> listar();
    void eliminarIngreso(Long id);
}
