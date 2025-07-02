package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.CompraInventarioDto;
import com.tecser.autopartes.application.service.CompraInventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraInventarioController {

    private final CompraInventarioService service;

    public CompraInventarioController(CompraInventarioService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CompraInventarioDto> crear(@RequestBody CompraInventarioDto dto) {
        return ResponseEntity.ok(service.guardarCompra(dto));
    }

    @PostMapping("/detalles-compra")
    public ResponseEntity<CompraInventarioDto> guardarCompraConDetalles(@RequestBody CompraInventarioDto dto) {
        return ResponseEntity.ok(service.guardarCompraConDetalles(dto));
    }


    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<CompraInventarioDto>> listar() {
        return ResponseEntity.ok(service.listarCompras());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{idCompra}")
    public ResponseEntity<CompraInventarioDto> buscarPorId(@PathVariable Long idCompra) {
        try {
            CompraInventarioDto dto = service.buscarPorId(idCompra);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PutMapping("/{idCompra}")
    public ResponseEntity<CompraInventarioDto> actualizar(
        @PathVariable Long idCompra, 
        @RequestBody CompraInventarioDto dto) {
        CompraInventarioDto actualizado = service.actualizarCompra(idCompra, dto);
        return ResponseEntity.ok(actualizado);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @DeleteMapping("/detalles/{idCompra}")
    public ResponseEntity<Void> eliminarCompraConDetalles(@PathVariable Long idCompra) {
        try {
            service.eliminarCompraConDetalles(idCompra);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
    }
}
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/mis-compras")
    public ResponseEntity<List<CompraInventarioDto>> obtenerMisCompras() {
        return ResponseEntity.ok(service.listarComprasPorUsuario());
    }
}
