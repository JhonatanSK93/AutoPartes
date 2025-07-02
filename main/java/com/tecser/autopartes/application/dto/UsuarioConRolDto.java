package com.tecser.autopartes.application.dto;

public class UsuarioConRolDto {
    private Long id;
    private String correo;
    private String nombreCompleto;
    private String rol;

    public UsuarioConRolDto(Long id, String correo, String nombreCompleto, String rol) {
        this.id = id;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getRol() {
        return rol;
    }
}
