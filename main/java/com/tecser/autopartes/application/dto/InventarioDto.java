package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.Inventario;

import java.math.BigDecimal;

public class InventarioDto {

    private String nombreParte;
    private String descripcion;
    private Integer cantidadDisponible;
    private String parteUbicacion;
    private BigDecimal precio;

    // --- Constructores ---
    public InventarioDto() {}

    public InventarioDto(String nombreParte, String descripcion, Integer cantidadDisponible, String parteUbicacion, BigDecimal precio) {
        this.nombreParte = nombreParte;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.parteUbicacion = parteUbicacion;
        this.precio = precio;
    }

    // --- Getters y Setters ---
    public String getNombreParte() {
        return nombreParte;
    }

    public void setNombreParte(String nombreParte) {
        this.nombreParte = nombreParte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getParteUbicacion() {
        return parteUbicacion;
    }

    public void setParteUbicacion(String parteUbicacion) {
        this.parteUbicacion = parteUbicacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    // --- Conversión DTO -> Entidad ---
    public Inventario toEntity() {
        return new Inventario(
                this.nombreParte,
                this.descripcion,
                this.cantidadDisponible,
                this.parteUbicacion,
                this.precio
        );
    }

    // --- Conversión Entidad -> DTO ---
    public static InventarioDto fromEntity(Inventario inventario) {
        return new InventarioDto(
                inventario.getNombreParte(),
                inventario.getDescripcion(),
                inventario.getCantidadDisponible(),
                inventario.getParteUbicacion(),
                inventario.getPrecio()
        );
    }
}
