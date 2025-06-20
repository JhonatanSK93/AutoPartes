package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.VentaClienteDto;
import com.tecser.autopartes.application.service.VentaClienteService;
import com.tecser.autopartes.domain.model.VentaCliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaClienteController {

    private final VentaClienteService ventaService;

    public VentaClienteController(VentaClienteService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaCliente> registrarVenta(@RequestBody VentaClienteDto dto) {
        VentaCliente venta = ventaService.guardarVenta(dto);
        return ResponseEntity.ok(venta);
    }

    @GetMapping
    public ResponseEntity<List<VentaCliente>> listarVentas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaCliente> obtenerPorId(@PathVariable String id) {
        return ventaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
