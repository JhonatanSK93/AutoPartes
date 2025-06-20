package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.IngresoInventarioDto;
import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.model.IngresoInventario;
import com.tecser.autopartes.domain.model.Parte;
import com.tecser.autopartes.domain.port.in.IngresoInventarioServicePort;
import com.tecser.autopartes.domain.port.out.CompraInventarioRepositoryPort;
import com.tecser.autopartes.domain.port.out.IngresoInventarioRepositoryPort;
import com.tecser.autopartes.domain.port.out.ParteRepositoryPort;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngresoInventarioService implements IngresoInventarioServicePort {

    private final IngresoInventarioRepositoryPort repository;
    private final CompraInventarioRepositoryPort compraRepository;
    private final ParteRepositoryPort parteRepository;

    public IngresoInventarioService(
            IngresoInventarioRepositoryPort repository,
            CompraInventarioRepositoryPort compraRepository,
            ParteRepositoryPort parteRepository) {
        this.repository = repository;
        this.compraRepository = compraRepository;
        this.parteRepository = parteRepository;
    }

        @Override
    public IngresoInventarioDto guardar(IngresoInventarioDto dto) {
        CompraInventario compra = compraRepository.buscarPorId(dto.getIdCompra())
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        Parte parte = parteRepository.buscarPorId(dto.getCodigoParte())
                .orElseThrow(() -> new RuntimeException("Parte no encontrada"));

        IngresoInventario ingreso = dto.toEntity(compra, parte);
        IngresoInventario guardado = repository.guardar(ingreso);

        return IngresoInventarioDto.fromEntity(guardado);
    }

    @Override
    public Optional<IngresoInventarioDto> buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .map(IngresoInventarioDto::fromEntity);
    }

    @Override
    public List<IngresoInventarioDto> listar() {
        return repository.listar()
                .stream()
                .map(IngresoInventarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarIngreso(Long id) {
        repository.eliminar(id);
    }
}
