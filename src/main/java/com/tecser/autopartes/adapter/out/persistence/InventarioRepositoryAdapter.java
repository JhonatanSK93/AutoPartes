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
    public Optional<Inventario> buscarPorId(Long idInventario) {
        return repository.findById(idInventario);
    }

    @Override
    public void eliminar(Long idInventario) {
        repository.deleteById(idInventario);
    }
}
