package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.VehiculoDto;

import java.util.List;

public interface VehiculoServicePort {
    VehiculoDto guardarVehiculo(VehiculoDto vehiculoDto);
    List<VehiculoDto> listarVehiculos();
    VehiculoDto obtenerVehiculoPorId(int id);
    VehiculoDto actualizarVehiculo(int id, VehiculoDto vehiculoDto);
    void eliminarVehiculo(int id);
}
