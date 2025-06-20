package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepositoryPort {
    Vehiculo guardar(Vehiculo vehiculo);
    List<Vehiculo> listar();
    Optional<Vehiculo> buscarPorId(String placa);
    Vehiculo actualizar(Vehiculo vehiculo);
    void eliminar(String placa);
}
