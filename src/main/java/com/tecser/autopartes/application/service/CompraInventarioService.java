package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.CompraInventarioDto;
import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.port.in.CompraInventarioServicePort;
import com.tecser.autopartes.domain.port.out.CompraInventarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraInventarioService implements CompraInventarioServicePort {

    private final CompraInventarioRepositoryPort repository;

    public CompraInventarioService(CompraInventarioRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CompraInventarioDto guardarCompra(CompraInventarioDto dto) {
        CompraInventario compra = dto.toEntity();
        return CompraInventarioDto.fromEntity(repository.guardar(compra));
    }

    @Override
    public List<CompraInventarioDto> listarCompras() {
        return repository.listar().stream()
                .map(CompraInventarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CompraInventarioDto buscarPorId(Long idCompra) {
        return repository.buscarPorId(idCompra)
                .map(CompraInventarioDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("CompraInventario no encontrada con ID: " + idCompra));
    }

    @Override
    public void eliminarCompra(Long idCompra) {
        repository.eliminar(idCompra);
    }

}
