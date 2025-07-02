package com.tecser.autopartes.application.dto;

import java.time.LocalDate;

import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.model.Usuario;
import com.tecser.autopartes.domain.model.Vehiculo;

public class VehiculoDto {

    private String placa;
    private String cedulaCliente;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private LocalDate fechaIngreso;
    private Long idUsuario; // ✅ Agregado

    // --- Getters y Setters ---

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente (String cedulaCliente) {
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

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    // --- Conversión DTO -> Entidad ---
    public Vehiculo toDomainModel() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(this.marca);
        vehiculo.setModelo(this.modelo);
        vehiculo.setAnio(this.anio);
        vehiculo.setPlaca(this.placa);
        vehiculo.setColor(this.color);
        vehiculo.setFechaIngreso(this.fechaIngreso);

        // Solo para asignación parcial (requiere fetch completo en el servicio)
        Usuario usuario = new Usuario();
        usuario.setId(this.idUsuario);
        vehiculo.setUsuario(usuario);

          // ✅ Agregar relación con Cliente
        Cliente cliente = new Cliente();
        cliente.setCedulaCliente(this.cedulaCliente); // Solo se asigna el ID (referencia)
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
        dto.setFechaIngreso(vehiculo.getFechaIngreso());

        if (vehiculo.getUsuario() != null) {
            dto.setIdUsuario(vehiculo.getUsuario().getId());
        }

        if (vehiculo.getCliente() != null) {
        dto.setCedulaCliente(vehiculo.getCliente().getCedulaCliente());
    }


        return dto;
    }
}
