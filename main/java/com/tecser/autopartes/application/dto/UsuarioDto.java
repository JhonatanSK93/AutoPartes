package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.Usuario;
import java.time.LocalDateTime;

public class UsuarioDto {

    private Long id;
    private String nombreCompleto;
    private String correo;
    private LocalDateTime createdAt;

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // --- Conversión Entidad → DTO ---
    public static UsuarioDto fromEntity(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setCorreo(usuario.getCorreo());
        dto.setCreatedAt(usuario.getCreatedAt());
        return dto;
    }

    // --- Conversión DTO → Entidad (opcional si se necesita) ---
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNombreCompleto(this.nombreCompleto);
        usuario.setCorreo(this.correo);
        return usuario;
    }
}
