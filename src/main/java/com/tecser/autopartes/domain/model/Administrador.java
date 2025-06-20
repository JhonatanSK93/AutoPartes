package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private String contrasena;

    // --- Constructores ---

    public Administrador() {}

    public Administrador(String cedula, String nombreCompleto, String contrasena) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.contrasena = contrasena;
    }

    // --- Getters y Setters ---

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
