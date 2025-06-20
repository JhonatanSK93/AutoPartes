package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.VentaClienteJpaRepository;
import com.tecser.autopartes.domain.model.VentaCliente;
import com.tecser.autopartes.domain.port.out.VentaClienteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VentaClienteRepositoryAdapter implements VentaClienteRepositoryPort {

    private final VentaClienteJpaRepository repository;

    public VentaClienteRepositoryAdapter(VentaClienteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public VentaCliente guardar(VentaCliente venta) {
        return repository.save(venta);
    }

    @Override
    public List<VentaCliente> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<VentaCliente> buscarPorId(String idVenta) {
        return repository.findById(idVenta);
    }

    @Override
    public void eliminar(String idVenta) {
        repository.deleteById(idVenta);
    }
}
