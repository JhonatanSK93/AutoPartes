package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.DetalleVentaDto;
import com.tecser.autopartes.domain.model.DetalleVenta;
import com.tecser.autopartes.domain.model.Parte;
import com.tecser.autopartes.domain.model.VentaCliente;
import com.tecser.autopartes.domain.port.out.DetalleVentaRepositoryPort;
import com.tecser.autopartes.adapter.out.persistence.repository.ParteJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.VentaClienteJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepositoryPort detalleVentaRepository;
    private final ParteJpaRepository parteRepository;
    private final VentaClienteJpaRepository ventaClienteRepository;

    public DetalleVentaService(
            DetalleVentaRepositoryPort detalleVentaRepository,
            ParteJpaRepository parteRepository,
            VentaClienteJpaRepository ventaClienteRepository
    ) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.parteRepository = parteRepository;
        this.ventaClienteRepository = ventaClienteRepository;
    }

    public DetalleVenta guardarDetalleVenta(DetalleVentaDto dto) {
        Parte parte = parteRepository.findById(dto.getCodigoParte())
                .orElseThrow(() -> new RuntimeException("Parte no encontrada"));

        VentaCliente venta = ventaClienteRepository.findById(dto.getIdVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        DetalleVenta detalle = new DetalleVenta();
        detalle.setVenta(venta);
        detalle.setParte(parte);
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());

        return detalleVentaRepository.guardar(detalle);
    }

    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.listar();
    }

    public Optional<DetalleVenta> obtenerDetallePorId(Long id) {
        return detalleVentaRepository.buscarPorId(id);
    }

    public void eliminarDetalle(Long id) {
        detalleVentaRepository.eliminar(id);
    }
}
