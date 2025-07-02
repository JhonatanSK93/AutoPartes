package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.Rol;

public class RolDto {
    private Long id;
    private String nombre;

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ✅ Conversión desde entidad
    public static RolDto fromEntity(Rol rol) {
        RolDto dto = new RolDto();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    // ✅ Conversión a entidad
    public Rol toEntity() {
        Rol rol = new Rol();
        rol.setId(this.id);
        rol.setNombre(this.nombre);
        return rol;
    }
}
