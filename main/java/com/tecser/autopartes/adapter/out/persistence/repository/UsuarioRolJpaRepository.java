package com.tecser.autopartes.adapter.out.persistence.repository;


import com.tecser.autopartes.domain.model.UsuarioRol;
import com.tecser.autopartes.domain.model.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRolJpaRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    Optional<UsuarioRol> findByUsuarioId(Long usuario);
    
    List<UsuarioRol> findByRolId(Long rolId);
    
    boolean existsByUsuarioIdAndRolId(Long usuarioId, Long rolId);

    void deleteByUsuarioId(Long usuarioId);
}
