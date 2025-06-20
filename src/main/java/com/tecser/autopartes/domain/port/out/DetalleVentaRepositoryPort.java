package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaRepositoryPort {

    DetalleVenta guardar(DetalleVenta detalle);

    List<DetalleVenta> listar();

    Optional<DetalleVenta> buscarPorId(Long id);

    void eliminar(Long id);
}
