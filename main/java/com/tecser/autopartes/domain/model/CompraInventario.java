package com.tecser.autopartes.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra_inventario")
public class CompraInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;

    private LocalDateTime fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompraInventario> detalles = new ArrayList<>();

    // --- Getters y Setters ---

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCompraInventario> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompraInventario> detalles) {
        this.detalles = detalles;
    }

    public void agregarDetalle(DetalleCompraInventario detalle) {
        detalles.add(detalle);
        detalle.setCompra(this);
    }

    public void quitarDetalle(DetalleCompraInventario detalle) {
        detalles.remove(detalle);
        detalle.setCompra(null);
    }
}
