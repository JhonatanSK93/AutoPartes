package com.tecser.autopartes.application.service;

import com.tecser.autopartes.application.dto.ParteDto;
import com.tecser.autopartes.domain.model.Parte;
import com.tecser.autopartes.domain.port.in.ParteServicePort;
import com.tecser.autopartes.domain.port.out.ParteRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ParteService implements ParteServicePort {

    private final ParteRepositoryPort parteRepository;

    public ParteService(ParteRepositoryPort parteRepository) {
        this.parteRepository = parteRepository;
    }

    @Override
    public ParteDto guardarParte(ParteDto dto) {
        Parte parte = dto.toDomainModel();

        // Si no se proporciona un ID, lo generamos
        if (parte.getCodigoParte() == null || parte.getCodigoParte().isEmpty()) {
            parte.setCodigoParte(generarCodigoParte(dto.getNombre()));
        }

        Parte guardada = parteRepository.guardar(parte);
        return ParteDto.fromDomainModel(guardada);
    }

    @Override
    public List<ParteDto> listarPartes() {
        return parteRepository.listar()
                .stream()
                .map(ParteDto::fromDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParteDto> obtenerPorId(String codigoParte) {
        return parteRepository.buscarPorId(codigoParte)
                .map(ParteDto::fromDomainModel);
    }

    @Override
    public ParteDto actualizarParte(String codigoParte, ParteDto dto) {
        Parte parte = dto.toDomainModel();
        parte.setCodigoParte(codigoParte);
        Parte actualizada = parteRepository.actualizar(parte);
        return ParteDto.fromDomainModel(actualizada);
    }

    @Override
    public void eliminarParte(String codigoParte) {
        parteRepository.eliminar(codigoParte);
    }

    private String generarCodigoParte(String nombre) {
        String siglas = nombre.length() >= 4
                ? nombre.substring(0, 4).toUpperCase()
                : nombre.toUpperCase();
        int numeroAleatorio = new Random().nextInt(9000) + 1000; // Entre 1000 y 9999
        return siglas + "_" + numeroAleatorio;
    }
}
