package com.tecser.autopartes.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecser.autopartes.application.dto.RolDto;
import com.tecser.autopartes.domain.port.in.RolServicePort;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolServicePort rolService;

    public RolController(RolServicePort rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public RolDto crearRol(@RequestBody RolDto rolDto) {
        return rolService.crearRol(rolDto);
    }

    @GetMapping
    public List<RolDto> listar() {
        return rolService.listarRoles();
    }
}
