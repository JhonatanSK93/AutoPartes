package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "partes")
public class Parte {

    @Id
    private String codigoParte; // Ej: "PRTA_0012"

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(name = "parte_ubicacion")
    private String parteUbicacion;

    private Double precio;

    @Column(name = "cantidad_actual")
    private Integer cantidadActual;

    // Constructor completo
    public Parte(String codigoParte, String nombre, String descripcion, String parteUbicacion, Double precio, Integer cantidadActual) {
        this.codigoParte = codigoParte;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.parteUbicacion = parteUbicacion;
        this.precio = precio;
        this.cantidadActual = cantidadActual;
    }

    // Constructor vacío
    public Parte() {}

    // Getters y Setters
    public String getCodigoParte() {
        return codigoParte;
    }

    public void setCodigoParte(String codigoParte) {
        this.codigoParte = codigoParte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParteUbicacion() {
        return parteUbicacion;
    }

    public void setParteUbicacion(String parteUbicacion) {
        this.parteUbicacion = parteUbicacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
}
