package com.tecser.autopartes.application.dto;


import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.model.IngresoInventario;


public class IngresoInventarioDto {

    private Long idDetalleCompra;
    private Long idCompra;
    private String codigoParte;
    private int cantidad;
    private Double precioUnitario;

    // --- Getters y Setters ---

    public Long getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Long idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public String getCodigoParte() {
        return codigoParte;
    }

    public void setCodigoParte(String codigoParte) {
        this.codigoParte = codigoParte;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // --- Convertir de entidad a DTO ---
    public static IngresoInventarioDto fromEntity(IngresoInventario entity) {
        IngresoInventarioDto dto = new IngresoInventarioDto();
        dto.setIdDetalleCompra(entity.getIdDetalleCompra());
           dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        return dto;
    }

    // --- Convertir de DTO a entidad ---
    public IngresoInventario toEntity(CompraInventario compra, String codigoParte){
        return new IngresoInventario(compra, cantidad, precioUnitario);
    }
}
