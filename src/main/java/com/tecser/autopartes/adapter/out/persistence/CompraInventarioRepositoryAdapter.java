package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.CompraInventarioJpaRepository;
import com.tecser.autopartes.domain.model.CompraInventario;
import com.tecser.autopartes.domain.port.out.CompraInventarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompraInventarioRepositoryAdapter implements CompraInventarioRepositoryPort {

    private final CompraInventarioJpaRepository repository;

    public CompraInventarioRepositoryAdapter(CompraInventarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompraInventario guardar(CompraInventario compra) {
        return repository.save(compra);
    }

    @Override
    public List<CompraInventario> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<CompraInventario> buscarPorId(Long idCompra) {
        return repository.findById(idCompra);
    }

    @Override
    public void eliminar(Long idCompra) {
        repository.deleteById(idCompra);
    }
}
