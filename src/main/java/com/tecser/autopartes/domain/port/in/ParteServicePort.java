package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.application.dto.ParteDto;
import java.util.List;
import java.util.Optional;


public interface ParteServicePort {
    ParteDto guardarParte(ParteDto dto);
    List<ParteDto> listarPartes();
    Optional <ParteDto> obtenerPorId(String codigoParte);
    ParteDto actualizarParte(String codigoParte, ParteDto dto);
    void eliminarParte(String codigoParte);
}