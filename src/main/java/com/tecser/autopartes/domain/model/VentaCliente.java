package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import java.util.List;

@Entity
@Table(name = "ventas")
public class VentaCliente {

    @Id
    private String idVenta;

    @ManyToOne
    @JoinColumn(name = "cliente_cedula", referencedColumnName = "cedulaCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "placa_vehiculo", referencedColumnName = "placa")
    private Vehiculo vehiculo;
    
    @ManyToOne
    @JoinColumn(name = "cedula_admin", referencedColumnName = "cedula", nullable = false )
    private Administrador administrador;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    private LocalDate fecha;
    private Double total;

    // Constructores
    public VentaCliente() {}

    public VentaCliente(String idVenta,Administrador administrador,Cliente cliente, LocalDate fecha, Double total) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.administrador = administrador;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y Setters
    public String getIdVenta() { return idVenta; }
    public void setIdVenta(String idVenta) { this.idVenta = idVenta; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Vehiculo getVehiculo() {return vehiculo;}
    public void setVehiculo(Vehiculo vehiculo) {this.vehiculo = vehiculo;}

    public List <DetalleVenta> getDetalleVentas() {return detalles;}
    public void setDetalles(List <DetalleVenta> detalles) {this.detalles = detalles;}

    public Administrador getAdministrador() { return administrador; }
    public void setAdministrador(Administrador administrador) { this.administrador = administrador; }
}