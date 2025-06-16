package com.tecser.autopartes.application.repository;

import com.tecser.autopartes.domain.model.Parte;

import java.util.List;
import java.util.Optional;

public interface ParteRepository {
    List<Parte> obtenerTodos();
    Optional<Parte> buscarPorId(String id);
    Parte guardar(Parte parte);
    void eliminar(String id);
}
