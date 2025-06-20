package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Parte;

import java.util.List;
import java.util.Optional;

public interface ParteRepositoryPort {

    Parte guardar(Parte parte);

    List<Parte> listar();

    Optional<Parte> buscarPorId(String codigoParte);

    Parte actualizar(Parte parte);

    void eliminar(String codigoParte);
}
