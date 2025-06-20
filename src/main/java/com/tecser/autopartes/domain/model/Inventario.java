package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;


@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "codigo_parte", nullable = false)

    private Parte parte;
    private Integer cantidadDisponible;


    // --- Constructores ---
    public Inventario() {
    }

    public Inventario(Parte parte, Integer cantidadDisponible) {
        this.parte = parte;
        this.cantidadDisponible = cantidadDisponible;
    }

    // --- Getters y Setters ---
    public Long  getId() {
        return idInventario;
    }

    public void setId(Long idInventario) {
        this.idInventario = idInventario;
    }

    public Parte getParte() {
        return parte;
    }

    public void setParte(Parte parte) {
        this.parte = parte;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
