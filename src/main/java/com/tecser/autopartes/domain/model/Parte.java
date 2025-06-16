package com.tecser.autopartes.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "partes")
public class Parte {

    @Id
    private String id;

    @Column(nullable = false)
    private String nombre;

    private String tipo;

    private String posicion;

    // Constructor completo
    public Parte(String id, String nombre, String tipo, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
    }

    // Constructor vacío
    public Parte() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }
}
