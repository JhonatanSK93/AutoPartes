package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.Parte;
import jakarta.validation.constraints.*;

public class ParteDto {

    @NotBlank(message = "El ID no puede estar en blanco")
    private String codigoParte;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @Size(min = 20, max = 150, message = "La descripción debe tener entre 20 y 150 caracteres")
    private String descripcion;

    private String parteUbicacion;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private double precio;

    @Min(value = 0, message = "La cantidad actual no puede ser negativa")
    private int cantidadActual;

    // --- Getters y Setters ---

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

    // --- Conversión DTO -> Entidad ---
    public Parte toDomainModel() {
        Parte parte = new Parte();
        parte.setCodigoParte(this.codigoParte);
        parte.setNombre(this.nombre);
        parte.setDescripcion(this.descripcion);
        parte.setParteUbicacion(this.parteUbicacion);
        parte.setPrecio(this.precio);
        parte.setCantidadActual(this.cantidadActual);
        return parte;
    }

    // --- Conversión Entidad -> DTO ---
    public static ParteDto fromDomainModel(Parte parte) {
        ParteDto dto = new ParteDto();
        dto.setCodigoParte(parte.getCodigoParte());
        dto.setNombre(parte.getNombre());
        dto.setDescripcion(parte.getDescripcion());
        dto.setParteUbicacion(parte.getParteUbicacion());
        dto.setPrecio(parte.getPrecio());
        dto.setCantidadActual(parte.getCantidadActual());
        return dto;
    }
}
