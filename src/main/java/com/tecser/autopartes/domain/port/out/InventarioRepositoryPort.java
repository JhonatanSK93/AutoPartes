package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioRepositoryPort {
    Inventario guardar(Inventario inventario);
    List<Inventario> listar();
    Optional<Inventario> buscarPorId(Long id);
    void eliminar(Long id);
}
