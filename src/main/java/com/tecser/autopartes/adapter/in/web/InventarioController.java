package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.InventarioDto;
import com.tecser.autopartes.application.service.InventarioService;
import com.tecser.autopartes.domain.model.Inventario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable Long idInventario) {
        return inventarioService.buscarPorId(idInventario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody InventarioDto dto) {
        Inventario creado = inventarioService.guardar(dto);
        return ResponseEntity.ok(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idInventario) {
        inventarioService.eliminar(idInventario);
        return ResponseEntity.noContent().build();
    }
}
