package com.tecser.autopartes.domain.port.in;

import com.tecser.autopartes.domain.model.Parte;
import java.util.List;

public interface ParteServicePort {
    List<Parte> obtenerAutopartes();
    Parte guardarAutoparte(Parte autoparte);
}