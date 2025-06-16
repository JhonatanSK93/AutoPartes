package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Parte;

import java.util.List;
import java.util.Optional;

public interface ParteRepositoryPort {
    List<Parte> findAll();
    Optional<Parte> findById(String id);
    Parte save(Parte parte);
    void deleteById(String id);
}
