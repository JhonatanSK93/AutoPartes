package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.VentaCliente;
import java.util.List;
import java.util.Optional;

public interface VentaClienteRepositoryPort {
    VentaCliente guardar(VentaCliente venta);
    List<VentaCliente> listar();
    Optional<VentaCliente> buscarPorId(String id);
    void eliminar(String id);
}
