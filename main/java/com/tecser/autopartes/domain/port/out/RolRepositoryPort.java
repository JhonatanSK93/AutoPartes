package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolRepositoryPort {
    Rol guardar(Rol rol);
    long count();
    void guardar(List<Rol> roles);
    List<Rol> listarTodos();
    Optional<Rol> buscarPorNombre(String nombre);

}