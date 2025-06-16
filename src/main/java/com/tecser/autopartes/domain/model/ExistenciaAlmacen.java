package com.tecser.autopartes.domain.model;

import java.time.LocalDateTime;

public class ExistenciaAlmacen {
    private String id;
    private String parteId;
    private int cantidad;
    private int slot;
    private LocalDateTime ultimaActualizacion;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getParteId() { return parteId; }
    public void setParteId(String parteId) { this.parteId = parteId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getSlot() { return slot; }
    public void setSlot(int slot) { this.slot = slot; }

    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }
    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) { this.ultimaActualizacion = ultimaActualizacion; }
}