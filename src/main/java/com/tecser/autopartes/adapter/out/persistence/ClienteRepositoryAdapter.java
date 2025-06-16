package com.tecser.autopartes.adapter.out.persistence;

import com.tecser.autopartes.adapter.out.persistence.repository.ClienteJpaRepository;
import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.port.out.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository jpaRepository;

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(String id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return jpaRepository.save(cliente);
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }
}
