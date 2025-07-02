package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @Column(name = "nombre_parte", nullable = false)
    private String nombreParte;  // Este es el ID único

    private String descripcion;

    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible;

    @Column(name = "parte_ubicacion")
    private String parteUbicacion;

    private BigDecimal precio;

    // --- Constructores ---
    public Inventario() {}

    public Inventario(String nombreParte, String descripcion, Integer cantidadDisponible, String parteUbicacion, BigDecimal precio) {
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
}
