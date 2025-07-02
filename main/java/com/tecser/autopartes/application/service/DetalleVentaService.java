package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.DetalleVentaDto;
import com.tecser.autopartes.domain.model.DetalleVenta;
import com.tecser.autopartes.domain.model.Inventario;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.VentaAlCliente;
import com.tecser.autopartes.domain.port.in.DetalleVentaServicePort;
import com.tecser.autopartes.domain.port.out.DetalleVentaRepositoryPort;
import com.tecser.autopartes.adapter.out.persistence.repository.InventarioJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.VentaAlClienteJpaRepository;
import com.tecser.autopartes.security.SecurityUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaService implements DetalleVentaServicePort {

    private final DetalleVentaRepositoryPort detalleVentaRepository;
    private final InventarioJpaRepository inventarioRepository;
    private final VentaAlClienteJpaRepository ventaRepository;

    public DetalleVentaService(
            DetalleVentaRepositoryPort detalleVentaRepository,
            InventarioJpaRepository inventarioRepository,
            VentaAlClienteJpaRepository ventaRepository
    ) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.inventarioRepository = inventarioRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public DetalleVentaDto guardar(DetalleVentaDto dto) {
        Usuario usuario = SecurityUtils.getUsuarioAutenticado();

        Inventario inventario = inventarioRepository.findById(dto.getNombreParte())
                .orElseThrow(() -> new RuntimeException("Parte no encontrada en Inventario: " + dto.getNombreParte()));

        VentaAlCliente venta = ventaRepository.findById(dto.getIdVenta())
                .filter(v -> v.getUsuario().getId().equals(usuario.getId()))
                .orElseThrow(() -> new RuntimeException("Venta no encontrada o no pertenece al usuario autenticado"));

        DetalleVenta detalle = dto.toEntity(inventario, venta);
        DetalleVenta guardado = detalleVentaRepository.guardar(detalle);

        return DetalleVentaDto.fromEntity(guardado);
    }

    @Override
    public List<DetalleVentaDto> listar() {
        return detalleVentaRepository.listar()
                .stream()
                .map(DetalleVentaDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DetalleVentaDto obtenerPorId(Long id) {
        return detalleVentaRepository.buscarPorId(id)
                .map(DetalleVentaDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + id));
    }

    @Override
    public void eliminar(Long id) {
        detalleVentaRepository.eliminar(id);
    }
}
