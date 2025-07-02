package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.VentaAlClienteDto;
import com.tecser.autopartes.domain.port.in.VentaAlClienteServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaAlClienteController {

    private final VentaAlClienteServicePort ventaService;

    public VentaAlClienteController(VentaAlClienteServicePort ventaService) {
        this.ventaService = ventaService;
    }

    // 🔐 Permiten crear venta: SUPER_ADMIN, ADMIN, VENDEDOR
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('VENDEDOR')")
    @PostMapping
    public ResponseEntity<VentaAlClienteDto> registrarVenta(@RequestBody VentaAlClienteDto dto) {
        VentaAlClienteDto ventaCreada = ventaService.guardarVenta(dto);
        return ResponseEntity.ok(ventaCreada);
    }

    // 🔐 Listar ventas: SUPER_ADMIN, ADMIN, VENDEDOR
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('VENDEDOR')")
    @GetMapping
    public ResponseEntity<List<VentaAlClienteDto>> listar() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    // 🔐 Ver detalle de venta por ID: SUPER_ADMIN, ADMIN, VENDEDOR
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<VentaAlClienteDto> buscarPorId(@PathVariable String id) {
        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔐 Solo SUPER_ADMIN puede eliminar ventas
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
