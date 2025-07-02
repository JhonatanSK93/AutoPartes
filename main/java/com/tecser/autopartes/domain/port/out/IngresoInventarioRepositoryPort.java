package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.IngresoInventario;

import java.util.List;
import java.util.Optional;

public interface IngresoInventarioRepositoryPort {

    IngresoInventario guardar(IngresoInventario ingreso);

    List<IngresoInventario> listar();

    Optional<IngresoInventario> buscarPorId(Long idDetalleCompra);

    void eliminar(Long idDetalleCompra);
}
