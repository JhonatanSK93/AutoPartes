package com.tecser.autopartes.adapter.in.web;

import com.tecser.autopartes.application.dto.AsignacionDto;
import com.tecser.autopartes.application.dto.VehiculoDto;
import com.tecser.autopartes.application.service.VehiculoService;
import com.tecser.autopartes.domain.model.Vehiculo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // ✅ Crear vehículo (asociado al usuario autenticado)
    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody VehiculoDto dto) {
        return ResponseEntity.ok(vehiculoService.guardar(dto));
    }

    // ✅ Listar todos los vehículos (opcional: uso administrativo)
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(vehiculoService.listarTodos());
    }

    //Asignar vehiculo al cliente
    @PutMapping("/asignar")
    public ResponseEntity<VehiculoDto> asignarCliente(@RequestBody AsignacionDto dto) {
            Vehiculo vehiculoActualizado = vehiculoService.asignarCliente(dto.getPlaca(), dto.getCedulaCliente());
            VehiculoDto respuesta = VehiculoDto.fromDomainModel(vehiculoActualizado);
            return ResponseEntity.ok(respuesta);
        }
    

    // ✅ Listar solo los vehículos del usuario autenticado
    @GetMapping("/mis vehiculos")
    public ResponseEntity<List<Vehiculo>> listarMisVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.listarMisVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

    // ✅ Buscar vehículo por placa
    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {
        return vehiculoService.buscarPorId(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Actualizar vehículo por placa
    @PutMapping("/{placa}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable String placa, @RequestBody VehiculoDto dto) {
        return vehiculoService.actualizar(placa, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar vehículo por placa
    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> eliminar(@PathVariable String placa) {
        vehiculoService.eliminar(placa);
        return ResponseEntity.noContent().build();
    }
}
