package com.tecser.autopartes.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String identificacion;

    private String telefono;

    public Cliente() {
    }

    public Cliente(String id, String nombre, String identificacion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
