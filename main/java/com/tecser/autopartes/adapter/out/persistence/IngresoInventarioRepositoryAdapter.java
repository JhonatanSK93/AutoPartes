package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.IngresoInventarioJpaRepository;
import com.tecser.autopartes.domain.model.IngresoInventario;
import com.tecser.autopartes.domain.port.out.IngresoInventarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IngresoInventarioRepositoryAdapter implements IngresoInventarioRepositoryPort {

    private final IngresoInventarioJpaRepository repository;

    public IngresoInventarioRepositoryAdapter(IngresoInventarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public IngresoInventario guardar(IngresoInventario ingreso) {
        return repository.save(ingreso);
    }

    @Override
    public List<IngresoInventario> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<IngresoInventario> buscarPorId(Long idDetalleCompra) {
        return repository.findById(idDetalleCompra);
    }

    @Override
    public void eliminar(Long idDetalleCompra) {
        repository.deleteById(idDetalleCompra);
    }
}
