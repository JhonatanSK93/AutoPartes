package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.model.DetalleCompraInventario;
import com.tecser.autopartes.domain.model.Inventario;

public class DetalleCompraInventarioDto {

    private Long idDetalle;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private Inventario inventario;  // Conecta con nombreParte como ID

    // --- Getters y Setters ---

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    // --- Conversión DTO → Entidad ---
    public DetalleCompraInventario toEntity(CompraInventario compra) {
    DetalleCompraInventario detalle = new DetalleCompraInventario();
    detalle.setIdDetalleCompra(this.idDetalle); // ✅ Este es el nombre correcto
    detalle.setDescripcion(this.descripcion);
    detalle.setCantidad(this.cantidad);
    detalle.setPrecioUnitario(this.precioUnitario);
    detalle.setInventario(this.inventario);
    detalle.setCompra(compra);
    return detalle;
}

    // --- Conversión Entidad → DTO ---
    public static DetalleCompraInventarioDto fromEntity(DetalleCompraInventario detalle) {
    DetalleCompraInventarioDto dto = new DetalleCompraInventarioDto();
    dto.setIdDetalle(detalle.getIdDetalleCompra()); // ✅ Este es el correcto
    dto.setDescripcion(detalle.getDescripcion());
    dto.setCantidad(detalle.getCantidad());
    dto.setPrecioUnitario(detalle.getPrecioUnitario());
    dto.setInventario(detalle.getInventario());
    return dto;
}
}
