package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.VentaAlCliente;
import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.model.DetalleVenta;
import com.tecser.autopartes.domain.model.Vehiculo;
import com.tecser.autopartes.domain.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VentaAlClienteDto {

    private String idVenta;
    private String cedulaCliente;
    private String placaVehiculo;
    private Long usuarioId; // 🔄 Antes era cedulaAdmin
    private LocalDate fecha;
    private Double total;

    private List<DetalleVentaDto> detalles;

    // --- Getters y Setters ---

    public String getIdVenta() { return idVenta; }
    public void setIdVenta(String idVenta) { this.idVenta = idVenta; }

    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public List<DetalleVentaDto> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaDto> detalles) { this.detalles = detalles; }

    // --- Conversión DTO → Entidad ---
    public VentaAlCliente toEntity(Cliente cliente, Vehiculo vehiculo, Usuario usuario, List<DetalleVenta> detalles) {
        VentaAlCliente venta = new VentaAlCliente();
        venta.setIdVenta(this.idVenta);
        venta.setFecha(this.fecha);
        venta.setTotal(this.total);
        venta.setCliente(cliente);
        venta.setVehiculo(vehiculo);
        venta.setUsuario(usuario); // ✅ cambiamos administrador por usuario
        venta.setDetalles(detalles);
        return venta;
    }

    // --- Conversión Entidad → DTO ---
    public static VentaAlClienteDto fromEntity(VentaAlCliente venta) {
        VentaAlClienteDto dto = new VentaAlClienteDto();
        dto.setIdVenta(venta.getIdVenta());
        dto.setFecha(venta.getFecha());
        dto.setTotal(venta.getTotal());

        if (venta.getCliente() != null) {
            dto.setCedulaCliente(venta.getCliente().getCedulaCliente());
        }

        if (venta.getVehiculo() != null) {
            dto.setPlacaVehiculo(venta.getVehiculo().getPlaca());
        }

        if (venta.getUsuario() != null) {
            dto.setUsuarioId(venta.getUsuario().getId()); // ✅ ahora con id de Usuario
        }

        if (venta.getDetalleVentas() != null) {
            dto.setDetalles(
                venta.getDetalleVentas().stream()
                    .map(DetalleVentaDto::fromEntity)
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
