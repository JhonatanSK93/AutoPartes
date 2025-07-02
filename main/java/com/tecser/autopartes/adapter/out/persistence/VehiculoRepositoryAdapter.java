package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.domain.model.Vehiculo;
import com.tecser.autopartes.domain.port.out.VehiculoRepositoryPort;
import com.tecser.autopartes.adapter.out.persistence.repository.VehiculoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehiculoRepositoryAdapter implements VehiculoRepositoryPort {

    private final VehiculoJpaRepository repository;

    public VehiculoRepositoryAdapter(VehiculoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    @Override
    public List<Vehiculo> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Vehiculo> buscarPorId(String placa) {
        return repository.findById(placa);
    }

    @Override
    public Vehiculo actualizar(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    @Override
    public void eliminar(String placa) {
        repository.deleteById(placa);
    }
}
