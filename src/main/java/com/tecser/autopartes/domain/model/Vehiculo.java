package com.tecser.autopartes.domain.model;

import java.util.UUID;

public class Vehiculo {
    private UUID id;
    private String marca;
    private String modelo;
    private String tipo;
    private int anio;
    private String color;
    private UUID administradorId;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public UUID getAdministradorId() { return administradorId; }
    public void setAdministradorId(UUID administradorId) { this.administradorId = administradorId; }
}
