package com.tecser.autopartes.application.dto;

public class DetalleVentaDto {

    private String codigoParte;
    private String idVenta;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    // --- Getters y Setters ---

    public String getCodigoParte() {
        return codigoParte;
    }

    public void setCodigoParte(String codigoParte) {
        this.codigoParte = codigoParte;
    }

    public String getIdVenta() {
    return idVenta;
}

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
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
}
