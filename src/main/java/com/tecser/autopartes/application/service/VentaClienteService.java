package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.VentaClienteDto;
import com.tecser.autopartes.domain.model.*;
import com.tecser.autopartes.adapter.out.persistence.repository.*;
import com.tecser.autopartes.domain.port.out.VentaClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

@Service
public class VentaClienteService {

    private final VentaClienteRepositoryPort ventaRepository;
    private final ClienteJpaRepository clienteRepository;
    private final VehiculoJpaRepository vehiculoRepository;
    private final ParteJpaRepository parteRepository;

    public VentaClienteService(VentaClienteRepositoryPort ventaRepository,
                        ClienteJpaRepository clienteRepository,
                        VehiculoJpaRepository vehiculoRepository,
                        ParteJpaRepository parteRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.parteRepository = parteRepository;
    }

    public VentaCliente guardarVenta(VentaClienteDto dto) {
        Cliente cliente = clienteRepository.findById(dto.getCedulaCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getPlacaVehiculo())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        VentaCliente venta = new VentaCliente();
        venta.setIdVenta(generarIdVenta(cliente));
        venta.setCliente(cliente);
        venta.setVehiculo(vehiculo);
        venta.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());
        venta.setTotal(dto.getTotal());

        List<DetalleVenta> detalles = dto.getDetalles().stream().map(detalleDto -> {
            Parte parte = parteRepository.findById(detalleDto.getCodigoParte())
                    .orElseThrow(() -> new RuntimeException("Parte no encontrada"));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setParte(parte);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setPrecioUnitario(detalleDto.getPrecioUnitario());
            detalle.setSubtotal(detalleDto.getSubtotal());
            detalle.setVenta(venta); // relacionar con venta actual

            return detalle;
        }).collect(Collectors.toList());

        venta.setDetalles(detalles);

        return ventaRepository.guardar(venta);
    }

    private String generarIdVenta(Cliente cliente) {
        String base = cliente.getNombre().split(" ")[0] + cliente.getApellido().split(" ")[0];
        String random = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        return base + "_" + random;
    }

    public List<VentaCliente> listarVentas() {
    return ventaRepository.listar();

    }

    public Optional<VentaCliente> obtenerPorId(String idVenta) {
    return ventaRepository.buscarPorId(idVenta);

    }

    public void eliminarVenta(String idVenta) {
    ventaRepository.eliminar(idVenta);
    }

}
