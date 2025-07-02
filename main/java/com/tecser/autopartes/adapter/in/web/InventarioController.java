package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.InventarioDto;
import com.tecser.autopartes.application.service.InventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // 🔐 SUPER_ADMIN y ADMIN pueden agregar o actualizar inventario
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<InventarioDto> guardar(@RequestBody InventarioDto dto) {
        return ResponseEntity.ok(inventarioService.guardar(dto));
    }

    // 🔐 Cualquier usuario autenticado puede consultar inventario
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<InventarioDto>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PutMapping("/{nombreParte}")
    public ResponseEntity<InventarioDto> actualizarInventario(
        @PathVariable String nombreParte, 
        @RequestBody InventarioDto dto) {
        
            InventarioDto actualizado = inventarioService.actualizar(nombreParte, dto);
            return ResponseEntity.ok(actualizado);
        }

    // 🔐 Solo SUPER_ADMIN puede eliminar piezas del inventario
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{nombreParte}")
    public ResponseEntity<Void> eliminar(@PathVariable String nombreParte) {
        inventarioService.eliminar(nombreParte);
        return ResponseEntity.noContent().build();
    }
}
