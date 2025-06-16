package com.tecser.autopartes.domain.model;

import java.util.UUID;

public class Administrador {
    private UUID id;
    private String nombre;
    private String clave;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
