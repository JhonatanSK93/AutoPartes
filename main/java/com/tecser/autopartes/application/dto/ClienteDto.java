package com.tecser.autopartes.application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;


public class ClienteDto {

   
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar en blanco")
    private String apellido;

    @NotBlank(message = "La cédula no puede estar en blanco")
    private String cedula;

    @Min(value = 1000000000L, message = "El teléfono debe tener al menos 7 dígitos")
    private Long telefono;

    private String direccion;

    @Email(message = "El correo electrónico no es válido")
    @Column(name = "correo_electronico")
    private String correoElectronico;

    //get y set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido= apellido;

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
