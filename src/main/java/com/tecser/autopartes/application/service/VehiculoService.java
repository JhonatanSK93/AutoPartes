package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.VehiculoDto;
import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.model.Vehiculo;
import com.tecser.autopartes.adapter.out.persistence.repository.ClienteJpaRepository;
import com.tecser.autopartes.adapter.out.persistence.repository.VehiculoJpaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VehiculoService {
    private final VehiculoJpaRepository vehiculoRepository;
    private final ClienteJpaRepository clienteRepository;

    public VehiculoService(VehiculoJpaRepository vehiculoRepository, ClienteJpaRepository clienteRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Vehiculo guardar(VehiculoDto dto) {
        Cliente cliente = clienteRepository.findById(dto.getCedulaCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCliente(cliente);
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setColor(dto.getColor());

        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> buscarPorId(String placa) {
        return vehiculoRepository.findById(placa);
    }

    public void eliminar(String placa) {
        vehiculoRepository.deleteById(placa);
    }
}