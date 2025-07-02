package com.tecser.autopartes.application.dto;

public class AsignacionDto {
    private String placa;
    private String cedulaCliente;

    public AsignacionDto(){}

    public String getPlaca(){
        return placa;
    }

    public void setPlaca (String placa) {
        this.placa = placa;
    }

    public String getCedulaCliente(){
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente){
        this.cedulaCliente = cedulaCliente;
    }
}
