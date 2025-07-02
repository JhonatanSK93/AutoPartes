package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.DetalleVenta;
import com.tecser.autopartes.domain.model.Inventario;
import com.tecser.autopartes.domain.model.VentaAlCliente;

public class DetalleVentaDto {

    private Long idDetalle;
    private String idVenta;
    private String nombreParte; // corresponde a Inventario.nombreParte
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    // --- Getters y Setters ---

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreParte() {
        return nombreParte;
    }

    public void setNombreParte(String nombreParte) {
        this.nombreParte = nombreParte;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    // --- Conversión DTO → Entidad ---
    public DetalleVenta toEntity(Inventario inventario, VentaAlCliente venta) {
        DetalleVenta entity = new DetalleVenta();
        entity.setIdDetalle(this.idDetalle);
        entity.setVenta(venta);
        entity.setNombreParte(inventario.getNombreParte());
        entity.setCantidad(this.cantidad);
        entity.setPrecioUnitario(this.precioUnitario);
        entity.setSubtotal(this.subtotal != null ? this.subtotal : this.cantidad * this.precioUnitario);
        return entity;
    }

    // --- Conversión Entidad → DTO ---
    public static DetalleVentaDto fromEntity(DetalleVenta entity) {
        DetalleVentaDto dto = new DetalleVentaDto();
        dto.setIdDetalle(entity.getIdDetalle());
        dto.setIdVenta(entity.getVenta().getIdVenta());
        dto.setNombreParte(entity.getNombreParte());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }
}
