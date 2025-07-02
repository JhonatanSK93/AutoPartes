package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServicePort {

    List<Cliente> obtenerClientes();

    Optional<Cliente> obtenerPorId(String CedulaCliente);

    Cliente guardarCliente(Cliente cedulaCliente);

    void eliminarCliente(String cedulaCliente);
}
