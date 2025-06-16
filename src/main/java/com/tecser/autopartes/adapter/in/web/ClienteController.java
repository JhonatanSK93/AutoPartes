package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.service.ClienteService;
import com.tecser.autopartes.domain.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.obtenerClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obtenerCliente(@PathVariable String id) {
        return clienteService.obtenerPorId(id);
    }

    @PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable String id) {
        clienteService.eliminarCliente(id);
    }
}
