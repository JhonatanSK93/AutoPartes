package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.DetalleVentaDto;
import com.tecser.autopartes.domain.port.in.DetalleVentaServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-venta")
public class DetalleVentaController {

    private final DetalleVentaServicePort service;

    public DetalleVentaController(DetalleVentaServicePort service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DetalleVentaDto> crear(@RequestBody DetalleVentaDto dto) {
        DetalleVentaDto creado = service.guardar(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaDto> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
