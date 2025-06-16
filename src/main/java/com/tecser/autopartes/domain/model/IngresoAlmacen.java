package com.tecser.autopartes.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class IngresoAlmacen {
    private String id;
    private String parteId;
    private int cantidadIngresada;
    private LocalDateTime fechaIngreso;
    private UUID administradorId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getParteId() { return parteId; }
    public void setParteId(String parteId) { this.parteId = parteId; }

    public int getCantidadIngresada() { return cantidadIngresada; }
    public void setCantidadIngresada(int cantidadIngresada) { this.cantidadIngresada = cantidadIngresada; }

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public UUID getAdministradorId() { return administradorId; }
    public void setAdministradorId(UUID administradorId) { this.administradorId = administradorId; }
}
