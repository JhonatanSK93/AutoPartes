package com.tecser.autopartes.application.dto;

import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.model.Vehiculo;


public class VehiculoDto {

    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private String cedulaCliente;

    // --- Getters y Setters ---

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa= placa;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor (String color) {
        this.color = color;
    }

     // --- Conversión DTO -> Entidad ---
    public Vehiculo toDomainModel() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(this.marca);
        vehiculo.setModelo(this.modelo);
        vehiculo.setAnio(this.anio);
        vehiculo.setPlaca(this.placa);
        vehiculo.setColor(this.color);

        Cliente cliente = new Cliente();
        cliente.setCedulaCliente(this.cedulaCliente);
        vehiculo.setCliente(cliente);

        return vehiculo;
    }

    // --- Conversión Entidad -> DTO ---
    public static VehiculoDto fromDomainModel(Vehiculo vehiculo) {
        VehiculoDto dto = new VehiculoDto();
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setAnio(vehiculo.getAnio());
        dto.setPlaca(vehiculo.getPlaca());
        dto.setColor(vehiculo.getColor());
        dto.setCedulaCliente(vehiculo.getCliente().getCedulaCliente());

        return dto;
    }
}
