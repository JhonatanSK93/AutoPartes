package com.tecser.autopartes.application.service;

import com.tecser.autopartes.domain.model.Parte;
import com.tecser.autopartes.domain.port.out.ParteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParteService {

    private final ParteRepositoryPort repository;

    public List<Parte> obtenerPartes() {
        return repository.findAll();
    }

    public Optional<Parte> obtenerPorId(String id) {
        return repository.findById(id);
    }

    public Parte guardarParte(Parte parte) {
        return repository.save(parte);
    }

    public void eliminarParte(String id) {
        repository.deleteById(id);
    }
}
