package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.Administrador;
import com.tecser.autopartes.domain.model.CompraInventario;

import java.time.LocalDateTime;

public class CompraInventarioDto {
    
    private Long idCompra;
    private LocalDateTime fechaCompra;
    private String cedula;

    // --- Getters y Setters ---

    public Long getIdCompra(){
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra){
        this.fechaCompra = fechaCompra;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula){
        this.cedula = cedula;
    }

    // --- Conversión DTO → Entidad ---
    public CompraInventario toEntity() {
        CompraInventario compra = new CompraInventario();
        Administrador admin = new Administrador();
        admin.setCedula(this.cedula);
        compra.setAdministrador(admin);
        compra.setFechaCompra(this.fechaCompra);
         compra.setIdCompra(this.idCompra);

        return compra;
    }

    // --- Conversión Entidad → DTO ---
    public static CompraInventarioDto fromEntity(CompraInventario compra) {
        CompraInventarioDto dto = new CompraInventarioDto();
        dto.setIdCompra(compra.getIdCompra());
        dto.setFechaCompra(compra.getFechaCompra());
        dto.setCedula(compra.getAdministrador().getCedula());

        return dto;
    }
}
