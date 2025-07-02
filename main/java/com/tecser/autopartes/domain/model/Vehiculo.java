package com.tecser.autopartes.domain.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    private String placa;

    @ManyToOne
    @JoinColumn(name = "cedulaCliente", referencedColumnName = "cedulaCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    private String marca;
    private String modelo;
    private Integer anio;
    private String color;


    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public Cliente getCliente() { return cliente;}
    public void setCliente(Cliente cliente) { this.cliente= cliente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getFechaIngreso() {return fechaIngreso;}
    public void setFechaIngreso(LocalDate fechaIngreso) {this.fechaIngreso = fechaIngreso;}
    
}
