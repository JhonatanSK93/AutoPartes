package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.ParteDto;
import com.tecser.autopartes.domain.port.in.ParteServicePort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/partes")
public class ParteController {

    private final ParteServicePort parteService;

    public ParteController(ParteServicePort parteService) {
        this.parteService = parteService;
    }

    @PostMapping
    public ResponseEntity<ParteDto> crearParte(@RequestBody ParteDto dto) {
        ParteDto creada = parteService.guardarParte(dto);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    public ResponseEntity<List<ParteDto>> listarPartes() {
        return ResponseEntity.ok(parteService.listarPartes());
    }

    @GetMapping("/{codigoParte}")
    public ResponseEntity<ParteDto> obtenerPartePorId(@PathVariable String codigoParte) {
        return parteService.obtenerPorId(codigoParte) 
        .map(ResponseEntity::ok).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parte no encontrada con ID: " + codigoParte));
    }

    @PutMapping("/{codigoParte}")
    public ResponseEntity<ParteDto> actualizarParte(@PathVariable String codigoParte, @RequestBody ParteDto dto) {
        ParteDto actualizada = parteService.actualizarParte(codigoParte, dto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{codigoParte}")
    public ResponseEntity<Void> eliminarParte(@PathVariable String codigoParte) {
        parteService.eliminarParte(codigoParte);
        return ResponseEntity.noContent().build();
    }
}
