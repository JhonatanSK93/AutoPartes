package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.InventarioJpaRepository;
import com.tecser.autopartes.domain.model.Inventario;
import com.tecser.autopartes.domain.port.out.InventarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InventarioRepositoryAdapter implements InventarioRepositoryPort {

    private final InventarioJpaRepository repository;

    public InventarioRepositoryAdapter(InventarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Inventario guardar(Inventario inventario) {
        return repository.save(inventario);
    }

    @Override
    public List<Inventario> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Inventario> buscarPorId(String idInventario) {
        return repository.findById(idInventario);
    }

    @Override
    public Optional<Inventario> buscarPorNombreParte(String nombreParte) {
        return repository.findByNombreParte(nombreParte);
    }

    @Override
    public void eliminar(String idInventario) {
        repository.deleteById(idInventario);
    }
}
