package com.tecser.autopartes.application.dto;

import java.time.LocalDate;
import java.util.List;

public class VentaClienteDto {

    private String idVenta;
    private String cedulaCliente;
    private String placaVehiculo;
    private List<DetalleVentaDto> detalles;
    private LocalDate fecha;
    private Double total;

    // --- Getters y Setters ---

    public String getIdVenta() { 
        return idVenta; 
    }

    public void setIdVenta(String idVenta) {
         this.idVenta = idVenta; 
    }


    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public List<DetalleVentaDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaDto> detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
