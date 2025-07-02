package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.VentaAlClienteDto;

import java.util.List;
import java.util.Optional;

public interface VentaAlClienteServicePort {

    VentaAlClienteDto guardarVenta(VentaAlClienteDto dto);
    List<VentaAlClienteDto> listarVentas();
    Optional<VentaAlClienteDto> buscarPorId(String idVenta);
    void eliminarVenta(String idVenta);

}
