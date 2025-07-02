package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.VentaAlClienteDto;
import com.tecser.autopartes.domain.model.*;
import com.tecser.autopartes.domain.port.in.VentaAlClienteServicePort;
import com.tecser.autopartes.domain.port.out.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaAlClienteService implements VentaAlClienteServicePort {

    private final VentaAlClienteRepositoryPort ventaRepository;
    private final ClienteRepositoryPort clienteRepository;
    private final VehiculoRepositoryPort vehiculoRepository;
    private final UsuarioRepositoryPort usuarioRepository;
    private final InventarioRepositoryPort inventarioRepository;
    public VentaAlClienteService(
        VentaAlClienteRepositoryPort ventaRepository,
        ClienteRepositoryPort clienteRepository,
        VehiculoRepositoryPort vehiculoRepository,
        UsuarioRepositoryPort usuarioRepository,
        InventarioRepositoryPort inventarioRepository,
        DetalleVentaRepositoryPort detalleVentaRepository
    ) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public VentaAlClienteDto guardarVenta(VentaAlClienteDto dto) {
        Cliente cliente = clienteRepository.findById(dto.getCedulaCliente())
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + dto.getCedulaCliente()));

        Usuario usuario = usuarioRepository.buscarPorId(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + dto.getUsuarioId()));

        Vehiculo vehiculo = null;
        if (dto.getPlacaVehiculo() != null) {
            vehiculo = vehiculoRepository.buscarPorId(dto.getPlacaVehiculo())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado: " + dto.getPlacaVehiculo()));
        }

        // Preparar detalles de la venta
        List<DetalleVenta> detalles = dto.getDetalles().stream().map(detalleDto -> {
            Inventario inventario = inventarioRepository.buscarPorId(detalleDto.getNombreParte())
                    .orElseThrow(() -> new RuntimeException("Parte no encontrada en inventario: " + detalleDto.getNombreParte()));

            if (inventario.getCantidadDisponible() < detalleDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para la parte: " + detalleDto.getNombreParte());
            }

            inventario.setCantidadDisponible(inventario.getCantidadDisponible() - detalleDto.getCantidad());
            inventarioRepository.guardar(inventario);

            return detalleDto.toEntity(inventario, null);
        }).collect(Collectors.toList());

        VentaAlCliente venta = dto.toEntity(cliente, vehiculo, usuario, detalles);
        detalles.forEach(detalle -> detalle.setVenta(venta));

        VentaAlCliente guardado = ventaRepository.guardar(venta);

        return VentaAlClienteDto.fromEntity(guardado);
    }

    @Override
    public List<VentaAlClienteDto> listarVentas() {
        return ventaRepository.listar().stream()
            .map(VentaAlClienteDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<VentaAlClienteDto> buscarPorId(String idVenta) {
        return ventaRepository.buscarPorId(idVenta)
                .map(VentaAlClienteDto::fromEntity);
    }

    @Override
    public void eliminarVenta(String idVenta) {
        ventaRepository.eliminar(idVenta);
    }
}
