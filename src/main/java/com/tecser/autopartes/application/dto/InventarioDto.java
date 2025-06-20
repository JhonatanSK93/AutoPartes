package com.tecser.autopartes.application.dto;


public class InventarioDto {
    private String id;
    private String idParte;
    private Integer cantidadDisponible;

    public InventarioDto() {
    }

    public InventarioDto(String id, String idParte, Integer cantidadDisponible) {
        this.id = id;
        this.idParte = idParte;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParte() {
        return idParte;
    }

    public void setIdParte(String idParte) {
        this.idParte = idParte;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
