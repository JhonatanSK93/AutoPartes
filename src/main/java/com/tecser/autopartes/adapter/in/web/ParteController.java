package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.service.ParteService;
import com.tecser.autopartes.domain.model.Parte;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partes")
public class ParteController {

    private final ParteService parteService;

    public ParteController(ParteService parteService) {
        this.parteService = parteService;
    }

    @GetMapping
    public List<Parte> listarPartes() {
        return parteService.obtenerPartes();
    }

    @GetMapping("/{id}")
    public Optional<Parte> obtenerParte(@PathVariable String id) {
        return parteService.obtenerPorId(id);
    }

    @PostMapping
    public Parte guardarParte(@RequestBody Parte parte) {
        return parteService.guardarParte(parte);
    }

    @DeleteMapping("/{id}")
    public void eliminarParte(@PathVariable String id) {
        parteService.eliminarParte(id);
    }
}
