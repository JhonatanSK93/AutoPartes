package com.tecser.autopartes.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Venta {
    private String id;
    private String parteId;
    private UUID vehiculoId;
    private UUID clienteId;
    private LocalDateTime fechaVenta;
    private UUID administradorId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getParteId() { return parteId; }
    public void setParteId(String parteId) { this.parteId = parteId; }

    public UUID getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(UUID vehiculoId) { this.vehiculoId = vehiculoId; }

    public UUID getClienteId() { return clienteId; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public UUID getAdministradorId() { return administradorId; }
    public void setAdministradorId(UUID administradorId) { this.administradorId = administradorId; }
}
