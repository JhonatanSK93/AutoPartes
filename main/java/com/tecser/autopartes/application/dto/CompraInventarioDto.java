package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class CompraInventarioDto {

    private Long idCompra;
    private LocalDateTime fechaCompra;
    private List<DetalleCompraInventarioDto> detalles;

    // --- Getters y Setters ---

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<DetalleCompraInventarioDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompraInventarioDto> detalles) {
        this.detalles = detalles;
    }

    // --- Conversión DTO → Entidad ---
    public CompraInventario toEntity(Usuario usuario) {
        CompraInventario compra = new CompraInventario();
        compra.setIdCompra(this.idCompra);
        compra.setFechaCompra(this.fechaCompra != null ? this.fechaCompra : LocalDateTime.now());
        compra.setUsuario(usuario);
        return compra;
    }

    // --- Conversión Entidad → DTO ---
    public static CompraInventarioDto fromEntity(CompraInventario compra) {
        CompraInventarioDto dto = new CompraInventarioDto();
        dto.setIdCompra(compra.getIdCompra());
        dto.setFechaCompra(compra.getFechaCompra());
        // El mapeo de detalles es opcional y puede hacerse en servicio si se requiere
        return dto;
    }
}
