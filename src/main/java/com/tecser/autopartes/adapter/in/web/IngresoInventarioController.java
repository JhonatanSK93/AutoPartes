package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.IngresoInventarioDto;
import com.tecser.autopartes.application.service.IngresoInventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoInventarioController {

    private final IngresoInventarioService service;

    public IngresoInventarioController(IngresoInventarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IngresoInventarioDto> crear(@RequestBody IngresoInventarioDto dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<IngresoInventarioDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoInventarioDto> buscarPorId(@PathVariable Long id) {
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
