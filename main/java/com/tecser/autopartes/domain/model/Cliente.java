package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "cedulaCliente")
    private String cedulaCliente;

    @Column(nullable = false)
    private String nombre;

    private String apellido;

    private Long telefono;

    private String direccion;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente")
    private List<Vehiculo> vehiculos;

    public Cliente() {
    }

    public Cliente(String cedulaCliente, String nombre, String apellido, Long telefono, String direccion, String correoElectronico) {
        this.cedulaCliente = cedulaCliente;
        this.nombre = nombre;
        this.apellido = apellido;         
        this.telefono = telefono;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

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
        this.apellido = apellido;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
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

    public List<Vehiculo> getVehiculos() {
    return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    
}
