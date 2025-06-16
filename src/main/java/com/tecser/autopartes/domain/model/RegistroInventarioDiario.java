package com.tecser.autopartes.domain.model;

import java.time.LocalDateTime;

public class RegistroInventarioDiario {
    private String id;
    private String parteId;
    private int cantidadExistente;
    private LocalDateTime fechaRegistro;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getParteId() { return parteId; }
    public void setParteId(String parteId) { this.parteId = parteId; }

    public int getCantidadExistente() { return cantidadExistente; }
    public void setCantidadExistente(int cantidadExistente) { this.cantidadExistente = cantidadExistente; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}