package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.CompraInventario;

import java.util.List;
import java.util.Optional;

public interface CompraInventarioRepositoryPort {

    CompraInventario guardar(CompraInventario compra);

    List<CompraInventario> listar();

    Optional<CompraInventario> buscarPorId(Long idCompra);

    void eliminar(Long idCompra);
}
