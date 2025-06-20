package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.VehiculoDto;
import com.tecser.autopartes.application.service.VehiculoService;
import com.tecser.autopartes.domain.model.Vehiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody VehiculoDto dto) {
        return ResponseEntity.ok(vehiculoService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(vehiculoService.listarTodos());
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {
        return vehiculoService.buscarPorId(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> eliminar(@PathVariable String placa) {
        vehiculoService.eliminar(placa);
        return ResponseEntity.noContent().build();
    }
}
