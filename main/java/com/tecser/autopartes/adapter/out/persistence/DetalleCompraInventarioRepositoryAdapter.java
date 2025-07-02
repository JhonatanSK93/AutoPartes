package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.DetalleCompraInventarioJpaRepository;
import com.tecser.autopartes.domain.model.DetalleCompraInventario;
import com.tecser.autopartes.domain.port.out.DetalleCompraInventarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DetalleCompraInventarioRepositoryAdapter implements DetalleCompraInventarioRepositoryPort {

    private final DetalleCompraInventarioJpaRepository jpaRepository;

    public DetalleCompraInventarioRepositoryAdapter(DetalleCompraInventarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public DetalleCompraInventario guardar(DetalleCompraInventario detalle) {
        return jpaRepository.save(detalle);
    }

    @Override
    public List<DetalleCompraInventario> listar() {
    return jpaRepository.findAll();
}

    @Override
    public Optional<DetalleCompraInventario> buscarPorId(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<DetalleCompraInventario> buscarPorCompraId(Long idCompra) {
        return jpaRepository.findByCompra_IdCompra(idCompra);
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void eliminarPorCompraId(Long idCompra) {
        jpaRepository.deleteByCompra_IdCompra(idCompra);
    }
}
