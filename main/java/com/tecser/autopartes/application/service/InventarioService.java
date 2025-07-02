package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.InventarioDto;
import com.tecser.autopartes.domain.model.Inventario;
import com.tecser.autopartes.domain.port.out.InventarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    private final InventarioRepositoryPort inventarioRepository;

    public InventarioService(InventarioRepositoryPort inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public InventarioDto guardar(InventarioDto dto) {
        Inventario inventario = dto.toEntity();
        Inventario guardado = inventarioRepository.guardar(inventario);
        return InventarioDto.fromEntity(guardado);
    }

    public List<InventarioDto> listar() {
        return inventarioRepository.listar()
                .stream()
                .map(InventarioDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<InventarioDto> buscarPorNombreParte(String nombreParte) {
        return inventarioRepository.buscarPorId(nombreParte)
                .map(InventarioDto::fromEntity);
    }

    public InventarioDto actualizar(String nombreParte, InventarioDto dto) {
        Inventario inventario = inventarioRepository.buscarPorId(nombreParte)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada: " + nombreParte));
        inventario.setCantidadDisponible(dto.getCantidadDisponible());
        inventario.setPrecio(dto.getPrecio());
        inventario.setDescripcion(dto.getDescripcion());

        Inventario actualizado = inventarioRepository.guardar(inventario);
        return InventarioDto.fromEntity(actualizado);
    }

    public void eliminar(String nombreParte) {
        inventarioRepository.eliminar(nombreParte);
    }
}
