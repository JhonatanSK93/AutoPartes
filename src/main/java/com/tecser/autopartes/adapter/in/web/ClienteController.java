package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.ClienteDto;
import com.tecser.autopartes.application.service.ClienteService;
import com.tecser.autopartes.domain.model.Cliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping()
    public List<ClienteDto> listarClientes() {
        return clienteService.obtenerClientes()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable String id) {
        return clienteService.obtenerPorId(id)
                .map(cliente -> ResponseEntity.ok(toDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteDto> guardarCliente(@Valid @RequestBody ClienteDto dto) {
        Cliente guardado = clienteService.guardarCliente(toEntity(dto));
        return ResponseEntity.ok(toDto(guardado));
    }

    @PutMapping("/{cedula}") // Usamos 'cedula' como el identificador en la URL
    public ResponseEntity<ClienteDto> actualizarCliente(@PathVariable String cedula, @Valid @RequestBody ClienteDto dto) {
        Optional<Cliente> clienteExistenteOpt = clienteService.obtenerPorId(cedula);
        if (clienteExistenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cliente clienteExistente = clienteExistenteOpt.get();
        clienteExistente.setNombre(dto.getNombre());
        clienteExistente.setApellido(dto.getApellido());
        clienteExistente.setTelefono(dto.getTelefono());
        clienteExistente.setDireccion(dto.getDireccion());
        clienteExistente.setCorreoElectronico(dto.getCorreoElectronico());

        Cliente clienteActualizado = clienteService.guardarCliente(clienteExistente);
        return ResponseEntity.ok(toDto(clienteActualizado));
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String cedulaCliente) {
        Optional<Cliente> existente = clienteService.obtenerPorId(cedulaCliente);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clienteService.eliminarCliente(cedulaCliente);
        return ResponseEntity.noContent().build();
    }

    // Métodos de mapeo entre Cliente y ClienteDto

    private ClienteDto toDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setCedula(cliente.getCedulaCliente());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setCorreoElectronico(cliente.getCorreoElectronico());
        return dto;
    }

    private Cliente toEntity(ClienteDto dto) {
        return new Cliente(
                dto.getCedula(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getTelefono(),
                dto.getDireccion(),
                dto.getCorreoElectronico()
        );
    }
}
