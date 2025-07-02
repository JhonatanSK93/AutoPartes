package com.tecser.autopartes.application.service;

import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.port.in.ClienteServicePort;
import com.tecser.autopartes.domain.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepository;

    public ClienteService(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(String id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void eliminarCliente(String id) {
        clienteRepository.deleteById(id);
    }
}
