package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "compra_inventario")
public class CompraInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    private LocalDateTime fechaCompra;

    @ManyToOne
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    private Administrador cedula;


     // Getters y Setters
    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Administrador getAdministrador() {
        return cedula;
    }

    public void setAdministrador(Administrador cedula) {
        this.cedula = cedula;
    }
}





