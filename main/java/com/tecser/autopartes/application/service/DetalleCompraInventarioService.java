// src/main/java/com/tecser/autopartes/application/service/DetalleCompraInventarioService.java
package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.DetalleCompraInventarioDto;
import com.tecser.autopartes.domain.model.DetalleCompraInventario;
import com.tecser.autopartes.domain.port.in.DetalleCompraInventarioServicePort;   // <‑‑ importa la interfaz
import com.tecser.autopartes.domain.port.out.DetalleCompraInventarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service                                                     // <-- Bean detectado por Spring
public class DetalleCompraInventarioService
        implements DetalleCompraInventarioServicePort {      // <-- IMPLEMENTA la interfaz

    private final DetalleCompraInventarioRepositoryPort repository;

    public DetalleCompraInventarioService(DetalleCompraInventarioRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public DetalleCompraInventarioDto guardar(DetalleCompraInventarioDto dto) {
        DetalleCompraInventario entidad = dto.toEntity(null);      // si aplica, pasa la compra
        DetalleCompraInventario guardado = repository.guardar(entidad);
        return DetalleCompraInventarioDto.fromEntity(guardado);
    }

    @Override
    public List<DetalleCompraInventarioDto> listar() {
        return repository.listar()
                .stream()
                .map(DetalleCompraInventarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DetalleCompraInventarioDto> buscarPorId(Long id) {
        return repository.buscarPorId(id).map(DetalleCompraInventarioDto::fromEntity);
    }

    @Override
    public void eliminarIngreso(Long id) {
        repository.eliminar(id);
    }
}
