package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.DetalleCompraInventario;

import java.util.List;
import java.util.Optional;

public interface DetalleCompraInventarioRepositoryPort {

    DetalleCompraInventario guardar(DetalleCompraInventario detalle);

    Optional<DetalleCompraInventario> buscarPorId(Long id);

    List<DetalleCompraInventario> buscarPorCompraId(Long idCompra);

    List<DetalleCompraInventario> listar(); // <-- ✅ Agregado

    void eliminar(Long id);

    void eliminarPorCompraId(Long idCompra);
}
