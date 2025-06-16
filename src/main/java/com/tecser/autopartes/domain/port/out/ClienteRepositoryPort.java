package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    List<Cliente> findAll();

    Optional<Cliente> findById(String id);

    Cliente save(Cliente cliente);

    void deleteById(String id);
}
