package com.tecser.autopartes.application.dto;

public class ParteDto {

    private String id;         // Ej: PUER_0001
    private String nombre;     // Ej: Puerta piloto
    private String tipo;       // Ej: Puerta, Motor, Llanta, etc.
    private String posicion;   // Ej: izquierdo, derecho, interno, o null si no aplica

    public ParteDto() {
    }

    public ParteDto(String id, String nombre, String tipo, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPosicion() {
        return posicion;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
