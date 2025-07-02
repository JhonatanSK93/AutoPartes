package com.tecser.autopartes.application.dto;

import java.util.List;

public class CrearUsuarioConNombresRolDto {

    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private List<String> nombresRoles; // Ej: ["ADMIN", "VENDEDOR"]

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<String> getNombresRoles() {
        return nombresRoles;
    }

    public void setNombresRoles(List<String> nombresRoles) {
        this.nombresRoles = nombresRoles;
    }
}
