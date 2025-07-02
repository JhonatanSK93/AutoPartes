package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.DetalleVentaJpaRepository;
import com.tecser.autopartes.domain.model.DetalleVenta;
import com.tecser.autopartes.domain.port.out.DetalleVentaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DetalleVentaRepositoryAdapter implements DetalleVentaRepositoryPort {

    private final DetalleVentaJpaRepository repository;

    public DetalleVentaRepositoryAdapter(DetalleVentaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        return repository.save(detalle);
    }

    @Override
    public List<DetalleVenta> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleVenta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
