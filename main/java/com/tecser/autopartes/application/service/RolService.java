package com.tecser.autopartes.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecser.autopartes.application.dto.RolDto;
import com.tecser.autopartes.domain.model.Rol;
import com.tecser.autopartes.domain.port.in.RolServicePort;
import com.tecser.autopartes.domain.port.out.RolRepositoryPort;

@Service
public class RolService implements RolServicePort {

    private final RolRepositoryPort rolRepository;

    public RolService(RolRepositoryPort rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public RolDto crearRol(RolDto rolDto) {
        Rol rol = rolDto.toEntity();
        return RolDto.fromEntity(rolRepository.guardar(rol));
    }

    @Override
    public List<RolDto> listarRoles() {
        return rolRepository.listarTodos().stream()
            .map(RolDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<RolDto> buscarPorNombre(String nombre) {
        return rolRepository.buscarPorNombre(nombre).map(RolDto::fromEntity);
    }
}
