package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.VentaAlClienteJpaRepository;
import com.tecser.autopartes.domain.model.VentaAlCliente;
import com.tecser.autopartes.domain.port.out.VentaAlClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VentaAlClienteRepositoryAdapter implements VentaAlClienteRepositoryPort {

    private final VentaAlClienteJpaRepository jpaRepository;

    public VentaAlClienteRepositoryAdapter(VentaAlClienteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public VentaAlCliente guardar(VentaAlCliente venta) {
        return jpaRepository.save(venta);
    }

    @Override
    public List<VentaAlCliente> listar() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<VentaAlCliente> buscarPorId(String idVenta) {
        return jpaRepository.findById(idVenta);
    }

    @Override
    public void eliminar(String idVenta) {
        jpaRepository.deleteById(idVenta);
    }
}
