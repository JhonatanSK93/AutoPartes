package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_compra_inventario")
public class DetalleCompraInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCompra;

    @ManyToOne
    @JoinColumn(name = "id_compra", nullable = false)
    private CompraInventario compra;

    @ManyToOne
    @JoinColumn(name = "nombre_parte", referencedColumnName = "nombre_Parte", nullable = false)
    private Inventario inventario;

    private String descripcion; // ✅ Agregar este campo
    private int cantidad;
    private Double precioUnitario;

    // --- Getters y Setters ---

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

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
