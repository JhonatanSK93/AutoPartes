package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.domain.model.Cliente;
import com.tecser.autopartes.domain.port.in.ClienteServicePort;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteServicePort clienteService;

    public ClienteController(ClienteServicePort clienteService) {
        this.clienteService = clienteService;
    }

    // 🔹 Crear o actualizar un cliente
    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        Cliente guardado = clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(guardado);
    }

    // 🔹 Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }

    // 🔹 Buscar cliente por cédula
    @GetMapping("/{cedula}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String cedula) {
        Optional<Cliente> cliente = clienteService.obtenerPorId(cedula);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar cliente por cédula
    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminar(@PathVariable String cedula) {
        clienteService.eliminarCliente(cedula);
        return ResponseEntity.noContent().build();
    }

    // ✅ Actualizar cliente por cédula
    @PutMapping("/{cedula}")
    public ResponseEntity<Cliente> actualizar(@PathVariable String cedula, @RequestBody Cliente clienteActualizado) {
        Optional<Cliente> clienteExistente = clienteService.obtenerPorId(cedula);

        if (clienteExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteExistente.get();
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setCorreoElectronico(clienteActualizado.getCorreoElectronico());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setDireccion(clienteActualizado.getDireccion());

        Cliente actualizado = clienteService.guardarCliente(cliente);
        return ResponseEntity.ok(actualizado);
    }
}
