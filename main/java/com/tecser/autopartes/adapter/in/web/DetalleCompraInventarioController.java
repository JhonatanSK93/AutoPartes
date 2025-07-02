// DetalleCompraInventarioController.java
package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.DetalleCompraInventarioDto;
import com.tecser.autopartes.domain.port.in.DetalleCompraInventarioServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-compra")
public class DetalleCompraInventarioController {

    private final DetalleCompraInventarioServicePort service;

    public DetalleCompraInventarioController(DetalleCompraInventarioServicePort service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DetalleCompraInventarioDto> guardar(@RequestBody DetalleCompraInventarioDto dto) {
        DetalleCompraInventarioDto guardado = service.guardar(dto);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public ResponseEntity<List<DetalleCompraInventarioDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompraInventarioDto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarIngreso(id);
        return ResponseEntity.noContent().build();
    }
}
