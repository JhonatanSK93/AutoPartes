package com.tecser.autopartes.domain.port.out;

import com.tecser.autopartes.domain.model.Usuario;

import java.util.Optional;
import java.util.List;

public interface UsuarioRepositoryPort {

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorCorreo(String correo);
    Usuario guardar (Usuario usuario);
    long contar();
    List<Usuario> listarTodos();
    void eliminar(Long id);
}
