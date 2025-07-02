package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;

@Entity
@Table (name ="ingreso_inventario")
public class IngresoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private CompraInventario compra;


    private Integer cantidad;

    private double precioUnitario;

    public IngresoInventario(CompraInventario compra, int cantidad, Double precioUnitario) {
        this.compra = compra;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public Long getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Long idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public CompraInventario getCompra() {
        return compra;
    }

    public void setCompra(CompraInventario compra) {
        this.compra = compra;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}



    
