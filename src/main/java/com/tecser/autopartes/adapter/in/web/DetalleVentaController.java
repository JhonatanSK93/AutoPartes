package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.DetalleVentaDto;
import com.tecser.autopartes.application.service.DetalleVentaService;
import com.tecser.autopartes.domain.model.DetalleVenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-ventas")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> crear(@RequestBody DetalleVentaDto dto) {
        return ResponseEntity.ok(detalleVentaService.guardarDetalleVenta(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        return ResponseEntity.ok(detalleVentaService.listarDetalles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> obtenerPorId(@PathVariable Long id) {
        return detalleVentaService.obtenerDetallePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleVentaService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
