package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.VentaAlCliente;

import java.util.List;
import java.util.Optional;

public interface VentaAlClienteRepositoryPort {

    VentaAlCliente guardar(VentaAlCliente venta);

    List<VentaAlCliente> listar();

    Optional<VentaAlCliente> buscarPorId(String idVenta);

    void eliminar(String idVenta);
}
