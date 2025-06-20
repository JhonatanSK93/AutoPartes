package com.tecser.autopartes.adapter.in.web;  

import com.tecser.autopartes.application.dto.CompraInventarioDto;
import com.tecser.autopartes.application.service.CompraInventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraInventarioController {

    private final CompraInventarioService service;

    public CompraInventarioController(CompraInventarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompraInventarioDto> crear(@RequestBody CompraInventarioDto dto) {
        return ResponseEntity.ok(service.guardarCompra(dto));
    }

    @GetMapping
    public ResponseEntity<List<CompraInventarioDto>> listar() {
        return ResponseEntity.ok(service.listarCompras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraInventarioDto> buscarPorId(@PathVariable Long idCompra) {
        try {
            CompraInventarioDto dto = service.buscarPorId(idCompra);
            return ResponseEntity.ok(dto);
    }   catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
    }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarCompra(id);
        return ResponseEntity.noContent().build();
    }
}
