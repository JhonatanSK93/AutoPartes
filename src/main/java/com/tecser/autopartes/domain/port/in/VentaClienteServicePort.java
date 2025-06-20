package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.VentaClienteDto;
import com.tecser.autopartes.domain.model.VentaCliente;

import java.util.List;

public interface VentaClienteServicePort {

    VentaCliente guardarVenta(VentaClienteDto dto);

    List<VentaCliente> listarVentas();

    VentaCliente obtenerPorId(String idVenta);

    void eliminarVenta(String idVenta);
}
